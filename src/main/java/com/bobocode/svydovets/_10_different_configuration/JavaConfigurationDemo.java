package com.bobocode.svydovets._10_different_configuration;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.config.JavaConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.User;

import java.util.HashMap;

/**
 This demo shows how to use Bibernate with a Java Configuration.
 In this example, a PostgreSQL database is configured with the appropriate properties using a HashMap.
 A SessionFactory is built with the configuration and used to open a session to find a User entity.
 This class showcases the ability to configure Bibernate programmatically with a Java Configuration object.
 */
public class JavaConfigurationDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        var configuration = new BibernateConfiguration();

        HashMap<String, String> properties = new HashMap<>();
        properties.put("svydovets.bibernate.driverClassName", "org.postgresql.Driver");
        properties.put("svydovets.bibernate.db.url", "jdbc:postgresql://localhost:5435/postgres");
        properties.put("svydovets.bibernate.db.username", "root");
        properties.put("svydovets.bibernate.db.password", "root");

        configuration.configure(new JavaConfiguration(properties));

        sessionFactory = configuration.buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.find(User.class, 1L);
        }
    }

}