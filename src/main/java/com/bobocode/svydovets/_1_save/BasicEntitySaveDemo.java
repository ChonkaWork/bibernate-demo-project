package com.bobocode.svydovets._1_save;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.Customer;
import com.bobocode.svydovets.entity.Order;
import com.bobocode.svydovets.entity.User;
import com.bobocode.svydovets.utils.DataFactory;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This demo shows how to save entities with different
 * {@link com.bobocode.svydovets.bibernate.annotation.GeneratedValue}
 * strategies. It demonstrates how to save an entity with an auto-generated ID
 * (using the default ID generation strategy), an entity with a sequence-generated ID,
 * and an entity with a manually specified ID.
 */
public class BasicEntitySaveDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();

        // Insert a new User with an auto-generated ID
        try (Session session = sessionFactory.openSession()) {
            User user = DataFactory.getDefaultUserWithoutId();
            session.save(user);
        }

        // Insert a new Customer with a custom sequence-generated ID
        try (Session session = sessionFactory.openSession()) {
            Customer customer = DataFactory.getDefaultCustomerWithoutId();
            session.save(customer);
        }

        // Insert a new Order with a manually specified ID
        try (Session session = sessionFactory.openSession()) {
            Order order = DataFactory.getDefaultOrderWithoutId();
            order.setId(ThreadLocalRandom.current().nextLong()); //random id
            session.save(order);
        }
    }

    private static void initSessionFactory() {
        var configuration = new BibernateConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
}