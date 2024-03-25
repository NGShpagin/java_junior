package lesson_4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Jpa {
    public static void main(String[] args) {
        // JPA Java Persistence API - набор правил (соглашений) по реализации доменной модели
        // Entity

        // Hibernate - одна из самых популярных реализаций стандарта JPA
        // EclipseLink

        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<User> users = LongStream.rangeClosed(1, 10)
                    .mapToObj(User::new)
                    .peek(it -> it.setName("User #" + it.getId()))
                    .peek(session::persist)
                    .toList();

//            User user = new User();
//            user.setName("Nik");
//            System.out.println("User.id before persist = " + user.getId());

//            session.persist(user);
//            System.out.println("User.id after persist = " + user.getId());

            session.getTransaction().commit();
        }

        final User loadedUser;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            loadedUser = session.get(User.class, 1);
            loadedUser.setName("Updated");
//
//            session.merge(loadedUser);
            session.getTransaction().commit();
        }


//        try (Session session = sessionFactory.openSession()) {
//            User loadedUser = session.get(User.class, 1);
//            System.out.println("user: " + loadedUser);
//        }

        try (Session session = sessionFactory.openSession()) {
            // jql - java query language
            List<User> users = session.createQuery("SELECT u FROM User u WHERE id >= 1 ORDER BY id DESC", User.class)
                    .getResultList();
            System.out.println(users);
        }
    }
}
