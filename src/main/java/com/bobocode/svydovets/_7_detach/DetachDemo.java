package com.bobocode.svydovets._7_detach;


import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.Order;
import com.bobocode.svydovets.utils.DataFactory;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This demo shows how to use the merge and detach methods in Bibernate.
 * It demonstrates how to create an entity, save it to the database, detach it from the persistence context,
 * update its state, merge it back into the persistence context, and then remove it from the database.
 */
@Slf4j
public class DetachDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();

        // Generate a random order ID
        long orderId = ThreadLocalRandom.current().nextLong();

        // Create a new Order instance with the generated ID and initial total price
        Order order;
        try (Session session = sessionFactory.openSession()) {
            order = DataFactory.getDefaultOrderWithoutId();
            order.setId(orderId);
            order.setTotalPrice(BigDecimal.valueOf(100.00));

            // Save the Order instance to the database
            session.save(order);

            // Detach the Order instance from the persistence context
            session.detach(order);

            // Update the total price of the detached Order instance
            order.setTotalPrice(new BigDecimal("150.00"));

            session.flush();
            log.info("Detached Order: {}", order);
            Order foundOrder = session.find(Order.class, orderId);
            log.info("Found Order: {}", foundOrder);
        }
    }

    private static void initSessionFactory() {
        var configuration = new BibernateConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
}
