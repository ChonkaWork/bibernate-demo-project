package com.bobocode.svydovets._10_different_configuration;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.config.PropertyFileConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.User;

/**
 This demo shows how to use Bibernate with a Properties Configuration.
 In this example, a PostgreSQL database is configured with the appropriate properties using a your properties file file.
 A SessionFactory is built with the configuration and used to open a session to find a User entity.
 */
public class PropertiesConfigurationDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        var configuration = new BibernateConfiguration();
        configuration.configure(new PropertyFileConfiguration("svydovets.properties"));

        sessionFactory = configuration.buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.find(User.class, 1L);
        }
    }

}