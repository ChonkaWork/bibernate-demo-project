package org.example._3_update;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.example.DataFactory;
import org.example.entity.Order;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class provides a basic scenario of how to update an entity using Bibernate's dirty checking mechanism.
 * It demonstrates how to save an entity with a manually specified ID,
 * update its state, and then save the updated entity back to the database using the dirty checking mechanism.
 */
@Slf4j
public class BasicEntityUpdateDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();

        // Insert a new Order with a manually specified ID
        long orderId = ThreadLocalRandom.current().nextLong();
        try (Session session = sessionFactory.openSession()) {
            Order order = DataFactory.getDefaultOrderWithoutId();
            order.setId(orderId); // random id
            order.setTotalPrice(new BigDecimal(100)); // initial total price
            session.save(order);
        }

        // Update the total price of the previously saved Order
        try (Session session = sessionFactory.openSession()) {
            Order order = session.find(Order.class, orderId);
            order.setTotalPrice(new BigDecimal(150)); // updated total price
            // the changes will be detected and the entity will be updated automatically when the session is flushed
        }

        // Retrieve and print the updated Order
        Session session = sessionFactory.openSession();
        Order order = session.find(Order.class, orderId);
        log.info("Order: {}", order);
        session.close();
    }

    private static void initSessionFactory() {
        var configuration = new BibernateConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
}
