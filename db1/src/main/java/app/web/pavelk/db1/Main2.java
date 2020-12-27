package app.web.pavelk.db1;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

public class Main2 {
    public static void main(String[] args) throws SQLException {

        //У фильма, который идет в кинотеатре, есть название,
        // длительность (пусть будет 60, 90 или 120 минут),
        // цена билета (в разное время и дни может быть разной),
        // время начала сеанса (один фильм может быть показан несколько раз в разное время и с разной ценой билета).
        // Есть информация о купленных билетах (номер билета, на какой сеанс).

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db1", "postgres", "0");
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS test;");
            statement.executeUpdate("drop table IF EXISTS test.cinema;");
            statement.executeUpdate("drop table IF EXISTS test.film;");
            statement.executeUpdate("drop table IF EXISTS test.ticket;");
            statement.executeUpdate("Create Table IF NOT EXISTS test.cinema(id_cinema SERIAL primary key , name varchar, address text)");
            statement.executeUpdate("Create Table IF NOT EXISTS test.film(id_film SERIAL primary key, id_cinema int, name varchar, time TIME )");
            statement.executeUpdate("Create Table IF NOT EXISTS test.ticket(id_ticket SERIAL primary key, id_film int, price int, date_time timestamp)");

            preparedStatement1 = connection.prepareStatement("INSERT into test.cinema (name, address)  values (?,?)");
            preparedStatement2 = connection.prepareStatement("INSERT into test.film (id_cinema, name, time)  values (?,?,?)");
            preparedStatement3 = connection.prepareStatement("INSERT into test.ticket (id_film, price, date_time)  values (?,?, ?)");

            for (int t = 1; t <= 2; t++) {
                preparedStatement1.setString(1, "cinema" + t);
                preparedStatement1.setString(2, "city" + t);
                preparedStatement1.executeUpdate();
                for (int i = 1; i <= 4; i++) {
                    preparedStatement2.setInt(1, t);
                    preparedStatement2.setString(2, "avatar " + i);
                    preparedStatement2.setTime(3, java.sql.Time.valueOf(LocalTime.of(i / 2, 30)));
                    preparedStatement2.executeUpdate();
                }

                for (int i = 0; i < 13 * t; i++) {
                    preparedStatement3.setInt(1, 1);
                    preparedStatement3.setInt(2, ThreadLocalRandom.current().nextInt(50, 550));
                    preparedStatement3.setTimestamp(3, java.sql.Timestamp.valueOf(
                            java.time.LocalDateTime.of(LocalDate.of(2020, 12, t), LocalTime.of(9, 30))));
                    preparedStatement3.executeUpdate();
                }

                for (int i = 0; i < 8 * t; i++) {
                    preparedStatement3.setInt(1, 2);
                    preparedStatement3.setInt(2, ThreadLocalRandom.current().nextInt(50, 550));
                    preparedStatement3.setTimestamp(3, java.sql.Timestamp.valueOf(
                            java.time.LocalDateTime.of(LocalDate.of(2020, 12, t), LocalTime.of(10, 30))));
                    preparedStatement3.executeUpdate();
                }

                for (int i = 0; i < 15 * t; i++) {
                    preparedStatement3.setInt(1, 3);
                    preparedStatement3.setInt(2, ThreadLocalRandom.current().nextInt(50, 550));
                    preparedStatement3.setTimestamp(3, java.sql.Timestamp.valueOf(
                            java.time.LocalDateTime.of(LocalDate.of(2020, 12, t), LocalTime.of(13, 30))));
                    preparedStatement3.executeUpdate();
                }

                for (int i = 0; i < 7 * t; i++) {
                    preparedStatement3.setInt(1, 4);
                    preparedStatement3.setInt(2, ThreadLocalRandom.current().nextInt(50, 550));
                    preparedStatement3.setTimestamp(3, java.sql.Timestamp.valueOf(
                            java.time.LocalDateTime.of(LocalDate.of(2020, 12, t), LocalTime.of(16, 30))));
                    preparedStatement3.executeUpdate();
                }
            }

            connection.commit();
            connection.setAutoCommit(true);

            ResultSet resultSet = statement.executeQuery("select " +
                    "tf.name, tt.date_time, tf.time " +
                    "from test.film as tf join test.ticket as tt on tt.id_film = tf.id_film " +
                    "group by tf.name, tt.date_time, tf.time " +
                    "order by tf.name"
            );

            int id = 0;
            while (resultSet.next()) {
                System.out.print(++id + " row ");
                System.out.print(" " + resultSet.getString(1));
                System.out.print(" начало " + resultSet.getString(2));
                System.out.println(" длительность " + resultSet.getString(3));
            }

            resultSet = statement.executeQuery("select " +
                    "tf.name, " +
                    "tt.date_time," +
                    "((select ttt.date_time from test.ticket as ttt where ttt.id_film = (tf.id_film + 1) limit  1) " +
                    "- (tt.date_time + tf.time)) as sm1, " +
                    "(select ttt.date_time from test.ticket as ttt where ttt.id_film = (tf.id_film + 1) limit  1)" +
                    "from test.film as tf join test.ticket as tt on tt.id_film = tf.id_film " +
                    "group by tf.name, tt.date_time, tf.id_film, sm1 " +
                    "order by sm1"
            );

            id = 0;
            while (resultSet.next()) {
                System.out.print(++id + " row ");
                System.out.print(" " + resultSet.getString(1));
                System.out.print(" начало " + resultSet.getString(2));
                System.out.print(" перерыв " + resultSet.getString(3));
                System.out.println(" начало следущего " + resultSet.getString(4));
            }

            resultSet = statement.executeQuery("select " +
                    "tf.name, count(tt), sum(tt.price),  AVG(tt.price) " +
                    "from test.film as tf join test.ticket as tt on tt.id_film = tf.id_film " +
                    "group by tf.name"
            );

            id = 0;
            while (resultSet.next()) {
                System.out.print(++id + " row ");
                System.out.print(" name " + resultSet.getString(1));
                System.out.print(" посетители " + resultSet.getLong(2));
                System.out.print(" сборы " + resultSet.getLong(3));
                System.out.println(" средний " + resultSet.getLong(4));
            }


            resultSet = statement.executeQuery("select " +
                    "tf.name, count(tt), sum(tt.price), date_trunc('day', tt.date_time) as dt " +
                    "from test.film as tf join test.ticket as tt on tt.id_film = tf.id_film " +
                    "group by tf.name, dt " +
                    "order by dt"
            );

            id = 0;
            while (resultSet.next()) {
                System.out.print(++id + " row ");
                System.out.print(" name " + resultSet.getString(1));
                System.out.print(" посетители " + resultSet.getLong(2));
                System.out.print(" сборы " + resultSet.getLong(3));
                System.out.println(" за день " + resultSet.getString(4));
            }


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            preparedStatement1.close();
            preparedStatement2.close();
            preparedStatement3.close();
            statement.close();
            connection.close();
        }
    }
}
