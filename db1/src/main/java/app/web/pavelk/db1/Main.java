package app.web.pavelk.db1;


import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db1", "postgres", "0");
            statement = connection.createStatement();
            statement.executeUpdate("drop table IF EXISTS test1.test2");

            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS test1;" +
                    "Create Table IF NOT EXISTS test1.test2(id SERIAL primary key , name varchar, address text)");

            for (int i = 0; i < 5; i++) {
                statement.executeUpdate("INSERT into test1.test2 (name, address)  values ('w" + i + "', 'a" + (i * 3) + "')");
            }

            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("INSERT into test1.test2 (name, address)  values (?,?)");
            preparedStatement.setString(1, "f1");
            preparedStatement.setString(2, "f1");
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, "f2");
            preparedStatement.setString(2, "f2");
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);

            ResultSet resultSet = statement.executeQuery("select tt.name, tt.address from test1.test2 as tt");

            int id = 0;
            while (resultSet.next())
            {
                System.out.print(++id + " Column 1 returned ");
                System.out.print(" " + resultSet.getString(1));
                System.out.println(" " + resultSet.getString(2));
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }finally {
            preparedStatement.close();
            statement.close();
            connection.close();
        }
    }
}

