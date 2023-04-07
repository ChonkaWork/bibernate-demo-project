package com.bobocode.svydovets._6_transaction;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.Customer;
import com.bobocode.svydovets.entity.Order;
import com.bobocode.svydovets.utils.DataFactory;
import com.bobocode.svydovets.entity.User;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This demo shows usage of transactions in Bibernate. It creates and saves new instances of
 * {@link Customer}, {@link Order}, and {@link User} entities, and performs all database operations within a single
 * transaction. If any exception occurs during the transaction, the transaction is rolled back to maintain database
 * consistency.
 */
public class TransactionDemo {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();

        Session session = sessionFactory.openSession();
        try {
            // Start a new transaction
            session.begin();

            // Create a new Customer entity and save it to the database
            Customer customer = DataFactory.getDefaultCustomerWithoutId();
            session.save(customer);

            // Create a new Order entity and associate it with the Customer entity
            Order order = DataFactory.getDefaultOrderWithoutId();
            order.setId(ThreadLocalRandom.current().nextLong());
            session.save(order);

            // Create a new User entity and save it to the database
            User user = DataFactory.getDefaultUserWithoutId();
            session.save(user);

            // Commit the transaction
            session.commit();
        } catch (Exception e) {
            // Rollback the transaction if an exception occurs
            session.rollback();
        } finally {
            session.close();
        }
    }

    private static void initSessionFactory() {
        var configuration = new BibernateConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

}
