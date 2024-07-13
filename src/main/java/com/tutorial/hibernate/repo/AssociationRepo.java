package com.tutorial.hibernate.repo;


import org.hibernate.Session;

import com.tutorial.hibernate.entity.User;
import com.tutorial.hibernate.utils.HibernateUtil;

public class AssociationRepo {

	public void addUser(User user) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	
	public User getUser(int id) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		return session.get(User.class,id);
	}
}
