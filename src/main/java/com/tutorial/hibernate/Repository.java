package com.tutorial.hibernate;

import org.hibernate.Session;

import com.tutorial.hibernate.utils.HibernateUtil;

/* CRUD -
 * This class contains DB operations
 */
public class Repository {

	public void addEmp(Emp emp) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		session.getTransaction().rollback();
		HibernateUtil.closeSession(session);
	}
}
