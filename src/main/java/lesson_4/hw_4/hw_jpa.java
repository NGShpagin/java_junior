package lesson_4.hw_4;

import lesson_4.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class hw_jpa {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            for (int i = 0; i < 5; i++) {
                Author author = new Author("author #" + i);
                session.persist(author);
            }

            session.getTransaction().commit();
        }



//        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sqlite")) {
//            try (Statement statement = connection.createStatement()) {
//                statement.executeUpdate("""
//                    CREATE TABLE IF NOT EXISTS book (
//                        id bigint,
//                        name varchar(255),
//                        author bigint
//                        )
//                    """);
//                System.out.println("Таблица book создана");
//
//                statement.executeUpdate("""
//                    CREATE TABLE IF NOT EXISTS author (
//                        id bigint,
//                        name varchar(255),
//                        )
//                    """);
//                System.out.println("Таблица author создана");
//
//                List<Author> authors = Stream
//                        .generate(Author::new)
//                        .peek(author -> author.setName("Author #" + author.getId()))
//                        .peek(author -> statement.)
////                        .peek(author -> {
////                            try {
////                                statement.executeUpdate("INSERT INTO authors");
////                            } catch (SQLException e) {
////                                throw new RuntimeException(e);
////                            }
////                        })
//                        .toList();
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e.getMessage());
//            }
//
//        }


    }


}
