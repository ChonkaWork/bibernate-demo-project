package com.bobocode.svydovets._5_optimisation;


import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.Order;
import com.bobocode.svydovets.utils.DataFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This demo shows the behavior of Bibernate when saving an entity multiple times within the same session.
 * Bibernate optimizes the persistence of the entity by executing only one INSERT statement, which can improve performance.
 * However, this approach can also lead to unexpected behavior if the entity has relationships with other entities.
 * In general, it's recommended to avoid saving the same entity multiple times within the same session.
 */
@Slf4j
public class MultipleSaveOptimisationDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();

        // Generate a random order ID
        long orderId = ThreadLocalRandom.current().nextLong();

        try (Session session = sessionFactory.openSession()) {
            Order order = DataFactory.getDefaultOrderWithoutId();
            order.setId(orderId);

            session.save(order);
            session.save(order);
            session.save(order);
            session.save(order);
            session.save(order);
            session.save(order);
        }

    }

    private static void initSessionFactory() {
        var configuration = new BibernateConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
}
