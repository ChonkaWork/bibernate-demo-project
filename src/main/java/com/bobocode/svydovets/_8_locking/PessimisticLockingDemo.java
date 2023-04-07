package com.bobocode.svydovets._8_locking;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.LockModeType;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.Order;
import com.bobocode.svydovets.utils.DataFactory;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * This demo shows pessimistic locking with Bibernate using the {@link LockModeType} enumeration.
 * The main method spawns two threads, one that locks an Order object with FOR_UPDATE mode and sleeps for 5 seconds,
 * and another thread that attempts to update the same Order object while it is still locked.
 * This should result in an exception being thrown by Bibernate.
 */
@Slf4j
public class PessimisticLockingDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) throws InterruptedException {
        initSessionFactory();

        Thread findThread = new Thread(() -> {
            try (Session session = sessionFactory.openSession()) {
                session.find(Order.class, DataFactory.DEFAULT_ID, LockModeType.FOR_UPDATE);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("Error occurred while locking the order", e);
                Thread.currentThread().interrupt();
            }
        });

        Thread updateThread = new Thread(() -> {
            try (Session newSession = sessionFactory.openSession()) {
                Order order = newSession.find(Order.class, DataFactory.DEFAULT_ID);
                order.setTotalPrice(BigDecimal.ONE);
            } catch (Exception e) {
                log.error("Error occurred while updating the order", e);
            }
        });

        findThread.start();
        updateThread.start();
        findThread.join();
        updateThread.join();
    }

    private static void initSessionFactory() {
        var configuration = new BibernateConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
}
