package lesson_4;

import java.sql.*;

public class Jdbc {
    public static void main(String[] args) throws SQLException {
        // RDBMS - Relation Database Management System (Postgres, MySQL, Oracle...)
        // SQL - Structure Query Language

        // JDBC Java Database Connectivity - набор классов в стандартной библиотеке,
        // которая предназначена для работы с реляционными БД

        // Driver
        // java.sql.Driver

        Connection connection = DriverManager.getConnection("jdbc:h2:mem:database.db");
        prepareTables(connection);
        executeInsert(connection);
        executeUpdate(connection);
        executeDelete(connection);
        executeSelect(connection);

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name FROM users WHERE name = ?");
        preparedStatement.setString(1, "User #1");
        ResultSet resultSet = preparedStatement.executeQuery();
        int counter = 0;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            System.out.println("(" + counter++ + ") id = " + id + ", name = " + name);
        }

        connection.close();
    }

    private static void prepareTables(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS users (
                        id bigint,
                        name varchar(255)
                        )
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeInsert(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    INSERT INTO users (id, name)
                    VALUES
                    (1, 'User #1'),
                    (2, 'User #2'),
                    (3, 'User #3'),
                    (4, 'User #4')
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeSelect(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name FROM users");
            int counter = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                System.out.println("(" + counter++ + ") id = " + id + ", name = " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeUpdate(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            int updateRows = statement.executeUpdate("UPDATE users SET name = 'unknown' WHERE id > 2");
            System.out.println(updateRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeDelete(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            int updateRows = statement.executeUpdate("DELETE FROM users WHERE id = 4");
            System.out.println(updateRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
