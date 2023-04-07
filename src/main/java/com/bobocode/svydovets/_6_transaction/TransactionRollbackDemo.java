package com.bobocode.svydovets._6_transaction;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.Customer;
import com.bobocode.svydovets.utils.DataFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

/**
 This demo shows the usage of transactions in Bibernate.
 Creates and saves a new {@link Customer} entity, attempts to find a
 non-existing entity, rolls back the transaction on failure, and ensures that the customer was not saved to the database.
 */
@Slf4j
public class TransactionRollbackDemo {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();

        Long customerId = null;
        Session session = sessionFactory.openSession();
        try {
            // Start a new transaction
            session.begin();

            // Create a new Customer entity and save it to the database
            Customer customer = DataFactory.getDefaultCustomerWithoutId();
            session.save(customer);
            customerId = customer.getId();

            //throws exception Unable to find entity this will cause rollback
            Customer notExistingCustomer = session.find(Customer.class, ThreadLocalRandom.current().nextLong());

            session.commit();
        } catch (Exception e) {
            log.error("Exception occurred", e);
            session.rollback();
        } finally {
            session.close();
        }

        Session otherSession = sessionFactory.openSession();
        Customer customer = otherSession.find(Customer.class, customerId);//throws exception Unable to find entity
        otherSession.close();
    }

    private static void initSessionFactory() {
        var configuration = new BibernateConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

}
