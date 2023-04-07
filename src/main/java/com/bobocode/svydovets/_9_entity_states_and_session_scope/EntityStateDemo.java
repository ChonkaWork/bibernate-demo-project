package com.bobocode.svydovets._9_entity_states_and_session_scope;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.Order;
import com.bobocode.svydovets.utils.DataFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

/**
 This demo shows usage of Bibernate sessions with regard to entity states.
 An Order entity is created, saved to a session, and detached from the session.
 An attempt to retrieve the entity in another session throws an exception since it is not available in that session.
 */
@Slf4j
public class EntityStateDemo {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            // Start a new transaction
            session.begin();

            // Create a new Order entity and add save action
            Order order = DataFactory.getDefaultOrderWithoutId();
            order.setId(ThreadLocalRandom.current().nextLong());

            log.info("Entity state before save: " + session.getEntityState(order));
            session.save(order);
            log.info("Entity state after save: " + session.getEntityState(order));
            session.detach(order);
            log.info("Entity state after detach: " + session.getEntityState(order));

            session.find(Order.class, order.getId());// throws error

            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
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
