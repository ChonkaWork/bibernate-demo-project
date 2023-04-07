package com.bobocode.svydovets._10_different_configuration;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.User;

/**
 This demo shows how to use Bibernate with a Properties Configuration.
 In this example, a PostgreSQL database is configured with the appropriate properties using a bibernate.properties file.
 A SessionFactory is built with the configuration and used to open a session to find a User entity.
 */
public class DefaultConfigurationDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        var configuration = new BibernateConfiguration();
        configuration.configure();

        sessionFactory = configuration.buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.find(User.class, 1L);
        }
    }

}