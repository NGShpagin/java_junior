package lesson_4.hw_4;

import java.sql.*;

/**
 * Задания необходимо выполнять на ЛЮБОЙ СУБД (postgresql, mysql, sqlite, h2, ...)
 * <p>
 * 1. С помощью JDBC выполнить:
 * 1.1 Создать таблицу book с колонками id bigint, name varchar, author varchar, ...
 * 1.2 Добавить в таблицу 10 книг
 * 1.3 Сделать запрос select from book where author = 'какое-то имя' и прочитать его с помощью ResultSet
 * <p>
 * 2. С помощью JPA (Hibernate) выполнить:
 * 2.1 Описать сущность Book из пункта 1.1
 * 2.2 Создать Session и сохранить в таблицу 10 книг
 * 2.3 Выгрузить список книг какого-то автора
 * <p>
 * 3.* Создать сущность Автор (id bigint, name varchar), и в сущности Book сделать поле типа Author (OneToOne)
 * 3.1 * Выгрузить Список книг и убедиться, что поле author заполнено
 * 3.2 ** В классе Author создать поле List<Book>, которое описывает список всех книг этого автора. (OneToMany)
 */

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:database.db")) {
            prepareTables(connection);
            executeInsert(connection);
            executeSelect(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void prepareTables(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS book (
                        id bigint,
                        name varchar(255),
                        author varchar(255)
                        )
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeInsert(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    INSERT INTO book (id, name, author)
                    VALUES
                    (1, 'User #1', 'Author #1'),
                    (2, 'User #2', 'Author #2'),
                    (3, 'User #3', 'Author #3'),
                    (4, 'User #4', 'Author #4'),
                    (5, 'User #5', 'Author #5'),
                    (6, 'User #6', 'Author #6'),
                    (7, 'User #7', 'Author #7'),
                    (8, 'User #8', 'Author #8'),
                    (9, 'User #9', 'Author #9'),
                    (10, 'User #10', 'Author #10')
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeSelect(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, author FROM book WHERE author = 'Author #4'");
            int counter = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");

                System.out.println("(" + counter++ + ") id = " + id + ", name = " + name + ", author = " + author);
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
