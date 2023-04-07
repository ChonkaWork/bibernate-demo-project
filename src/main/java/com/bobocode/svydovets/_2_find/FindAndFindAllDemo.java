package com.bobocode.svydovets._2_find;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import lombok.extern.slf4j.Slf4j;
import com.bobocode.svydovets.entity.Customer;
import com.bobocode.svydovets.entity.User;

import java.util.Collection;

/**
 * This demo shows how to find an entity by ID and how to find all entities of a certain type.
 */
@Slf4j
public class FindAndFindAllDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            // Find an entity by ID
            User user = session.find(User.class, 1L);
            log.info("Found user: {}", user);

            // Find all entities
            Collection<Customer> customers = session.findAll(Customer.class);
            customers.forEach(customer -> log.info("Found customer: {}", customer));
        }
    }

    private static void initSessionFactory() {
        var configuration = new BibernateConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
}
