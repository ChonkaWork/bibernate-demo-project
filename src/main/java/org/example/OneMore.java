package org.example;

import com.bobocode.svydovets.bibernate.config.BibernateConfiguration;
import com.bobocode.svydovets.bibernate.session.Session;
import com.bobocode.svydovets.bibernate.session.SessionFactory;
import org.example.entity.User;

public class OneMore {
    public static void main(String[] args) {
        BibernateConfiguration configuration = new BibernateConfiguration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        User user11 = new User();

        user11.setFirstName("M1");

        Session session1 = sessionFactory.openSession();

        session1.save(user11);

        session1.close();

//        Session session = sessionFactory.openSession();
//
//        session.save(user11);
//
//        session.close();
    }
}
