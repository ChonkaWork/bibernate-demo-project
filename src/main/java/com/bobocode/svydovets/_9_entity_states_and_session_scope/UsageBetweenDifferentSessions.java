package com.bobocode.svydovets._9_entity_states_and_session_scope;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import com.bobocode.svydovets.entity.Order;
import com.bobocode.svydovets.utils.DataFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This demo shows usage Bibernate sessions and entity states.
 * The Order entity is in MANAGED state within the current session and in TRANSIENT state in the otherSession.
 * Trying to fetch the Order in the otherSession before closing the current session will throw an exception
 */
@Slf4j
public class UsageBetweenDifferentSessions {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();
        Session session = sessionFactory.openSession();
        Session otherSession = sessionFactory.openSession();

        try {
            // Start a new transaction
            session.begin();

            // Create a new Order entity and add save action
            long id = ThreadLocalRandom.current().nextLong();
            Order order = DataFactory.getDefaultOrderWithoutId();
            order.setId(id);

            session.save(order);

            // Check the entity state in the current session
            log.info(String.valueOf(session.getEntityState(order)));
            // Check the entity state in the other session
            log.info(String.valueOf(otherSession.getEntityState(order)));

            // Attempt to retrieve the order in the other session before closing the original session
            Order retrievedOrder = otherSession.find(Order.class, id); // throws error

            session.commit();
        } catch (Exception e) {
            log.error("Exception occurred", e);
            session.rollback();
        } finally {
            session.close();
            otherSession.close();
        }
    }

    private static void initSessionFactory() {
        var configuration = new BibernateConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

}
