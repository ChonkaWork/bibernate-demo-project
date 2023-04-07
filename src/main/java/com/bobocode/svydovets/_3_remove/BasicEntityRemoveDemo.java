package com.bobocode.svydovets._3_remove;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.Order;
import com.bobocode.svydovets.utils.DataFactory;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This demo shows how to remove an entity using the Bibernate ORM framework.
 * It demonstrates how to save an entity with a manually specified ID,
 * and then remove it.
 */
public class BasicEntityRemoveDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();

        // Insert a new Order with a manually specified ID
        long orderId = ThreadLocalRandom.current().nextLong();
        try (Session session = sessionFactory.openSession()) {
            Order order = DataFactory.getDefaultOrderWithoutId();
            order.setId(orderId); //random id
            session.save(order);
        }

        // Remove the previously saved Order
        try (Session session = sessionFactory.openSession()) {
            Order order = session.find(Order.class, orderId);
            session.delete(order);
        }
    }

    private static void initSessionFactory() {
        var configuration = new BibernateConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
}