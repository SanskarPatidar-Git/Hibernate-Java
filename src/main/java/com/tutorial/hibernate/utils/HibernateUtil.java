package com.tutorial.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.tutorial.hibernate.entity.Emp;
import com.tutorial.hibernate.entity.Order;
import com.tutorial.hibernate.entity.User;

/* 
 * This class is responsible for creating and providing objects of
 * SessionFactory and Sessions.
 * 
 * .addAnotatedClass(.class)
 * Mapping -> Tell hibernate to map this class to Database.
 * either you use this annotation or map the classed in hibernate.cfg.xml
 * using mapping tag.
 * <mapping class="com.example.YourEntity"/>
 */

public class HibernateUtil {

	private static SessionFactory sessionFactory;

    static {
    	
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            
            sessionFactory = configuration
            		.addAnnotatedClass(Emp.class)
            		.addAnnotatedClass(User.class)
            		.addAnnotatedClass(Order.class)
            		.buildSessionFactory(serviceRegistry);
            
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session getSession() {
		return sessionFactory.openSession();
	}
    
    public static void closeSession(Session session) {
		if(session != null) {
			session.close();
		}
	}
}
