package app.web.pavelk.db1;

import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

class Main31 {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement1 = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db1", "postgres", "0");
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS test3;");
            statement.executeUpdate("drop table IF EXISTS test3.test1;");
            statement.executeUpdate(
                    "Create Table IF NOT EXISTS test3.test1(id_ticket SERIAL primary key, id_film int, price int, name1 varchar(250) , name2 varchar(250))");

            for (int j = 0; j < 7; j++) {
                preparedStatement1 = connection.prepareStatement("INSERT into test3.test1(id_film, price, name1, name2)  values (?,?,?,?)");
                for (int i = 0; i < 100_000; i++) {
                    preparedStatement1.setInt(1, ThreadLocalRandom.current().nextInt(1, 10));
                    preparedStatement1.setInt(2, ThreadLocalRandom.current().nextInt(1, 10));
                    preparedStatement1.setString(3, generate());
                    preparedStatement1.setString(4, generate());
                    preparedStatement1.executeUpdate();
                }
                connection.commit();
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            preparedStatement1.close();
            statement.close();
            connection.close();
        }
    }

    public static String generate() {
        return ThreadLocalRandom.current().ints(97, 122)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}

class Main32 {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db1", "postgres", "0");
            statement = connection.createStatement();

            long time1 = System.currentTimeMillis();

            //1
            ResultSet resultSet = statement.executeQuery("select " +
                    "t31.name1, t31.price " +
                    "from test3.test1 as t31 " +
                    "where t31.price = 2 " +
                    "group by t31.name1, t31.price "
            );

            //2
//            ResultSet resultSet = statement.executeQuery("select " +
//                    "t31.name1, t31.price " +
//                    "from test3.test1 as t31 " +
//                    "where t31.name1 like 'a%' " +
//                    "group by t31.name1, t31.price "
//            );
            System.out.println((System.currentTimeMillis() - time1));

            int id = 0;
            while (resultSet.next()) {
                ++id;
            }
            System.out.println(id);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public static String generate() {
        return ThreadLocalRandom.current().ints(97, 122)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}

class Main33 {
    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db1", "postgres", "0");
            statement = connection.createStatement();
            connection.setAutoCommit(false);

            //--1 t31.price = 2
            //без индекса 1 - 273 2 - 133 3 - 107  4 - 92 5 - 96 6 - 118
            //с индексом 1 - 92 2 - 78 3 - 80 4 - 79 5 - 76 6 - 85 7 - 89 8 - 81 9 - 86
//            statement.executeUpdate("CREATE INDEX index1_test3 ON test3.test1 (price);");
//            statement.executeUpdate("DROP INDEX  IF EXISTS test3.index1_test3;");

            //--2 t31.name1 like 'a%'
            //без индекса 146, 110, 107, 118, 117, 110
            //с индексом 113, 112, 104, 120, 117, 113, 112
//            statement.executeUpdate("CREATE INDEX index2_test3 ON test3.test1 (name1);");
//            statement.executeUpdate("DROP INDEX  IF EXISTS test3.index2_test3;");

            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            statement.close();
            connection.close();
        }
    }
}
