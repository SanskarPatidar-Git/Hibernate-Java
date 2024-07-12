package com.tutorial.hibernate;

import org.hibernate.Session;

import com.tutorial.hibernate.utils.HibernateUtil;

/* CRUD -
 * This class contains DB operations.
 * For each transaction/operation we need a Session object which is provided by SessionFactory in HibernateUtil class
 */
public class Repository {

	public void addEmp(Emp emp) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		HibernateUtil.closeSession(session);
	}
	
	/*
	 * getCurrentSession()
	 * We can also get session by getCurrentSession() and openSession()
	 * getCurrentSession() is used mostly where hibernate manages the session lifecycles.
	 * If using Spring and Java EE manages automatically
	 * 
	 * opneSession()
	 * When you want to manage it manually.
	 */
	public Emp getEmp(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session.get(Emp.class,id);
	}
}
