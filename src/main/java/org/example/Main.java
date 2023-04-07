package org.example;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import org.example.entity.User;


public class Main {
    public static void main(String[] args) {
        BibernateConfiguration configuration = new BibernateConfiguration();
        // TODO: 06.04.23  check different configs
        configuration.configure();

        User user1 = new User();
        user1.setId(12L);
        user1.setFirstName("Hello wtf");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session1 = sessionFactory.openSession();

        User user = session1.find(User.class, 12L);

        session1.detach(user);
        session1.close();

        Session session = sessionFactory.openSession();

        session.merge(user);

        user.setFirstName("sdasdasd");
        session.close();

        // TODO: 06.04.23 create this case and show optimisation

//        User user1 = new User();
//        User user2 = new User();
//        user1.setFirstName("Vardan111");
//        user2.setFirstName("Vardan222");
//
//        session.save(user1);
//
//        user1.setFirstName("new name");
//
//        session.save(user2);
//
//        session.delete(user1);

//        User user = session.find(User.class, 10);
//        user.setFirstName("Oleh111");

//        session.close();

//        try {
//            session.begin();
//
//
//            session.commit();
//        } catch (Exception ex) {
//            session.rollback();
//        }
    }
}