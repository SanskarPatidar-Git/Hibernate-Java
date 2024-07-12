package com.tutorial.hibernate.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.tutorial.hibernate.entity.Emp;
import com.tutorial.hibernate.utils.HibernateUtil;

/* CRUD -
 * This class contains DB operations.
 * For each transaction/operation we need a Session object which is provided by SessionFactory in HibernateUtil class
 */
public class Repository {

	//Create
	public void addEmp(Emp emp) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.save(emp); //or persist()
			session.getTransaction().commit();
		} catch (Exception e) {
			System.err.println("Repository::addEmp() Error:"+e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/* Read
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
		return session.find(Emp.class, id); //Or use get()
	}
	
	//Update
	public void updateEmp(Emp emp) {
		try {
			Session session = getTransactionSession();
			session.update(emp);
			session.getTransaction().commit();
			session.close();
		}catch(Exception e) {
			System.err.println("Repository::updateEmp() Error:"+e.getMessage());
		}
	}
	
	//Delete
	public void deleteEmp(Emp emp) {
		try {
			Session session = getTransactionSession();
			session.delete(emp);
			session.getTransaction().commit();
			session.close();
		} catch(Exception e) {
			System.err.println("Repository::deleteEmp() Error:"+e.getMessage());
		}
	}
	
	/* Get all-
	 * There is no specific method to get all records from a table.
	 * So we need to write a HQL(Hibernate query language) to get all the data.
	 * Write a query to get all records.
	 * Hibernate will convert this HQL to db specific query.
	 */
	public List<Emp> getAllEmp() {
		Session session = getTransactionSession();
		Query<Emp> query = session.createQuery("FROM Emp",Emp.class);
		return query.getResultList();
	}
	
	/* Persistent-
	 * When you attach an object to hibernate the state of object change from transient to persistent.
	 * Hibernate associates the connection to that object and when you change the fields of that object
	 * it will also reflected to associated object.
	 * Earlier we was passing emp salary = 15000 later changed it with 45000.
	 * This mechanism is called Persistent and for more you need to check state of objects.
	 */
	public void persistObject(Emp emp) {
		Session session = getTransactionSession();
		session.save(emp); //Object associated with hibernate
		
		emp.setSalary(45000); //Changing the content of object also changed in persistent object
		session.getTransaction().commit();
	}
	
	
	private Session getTransactionSession() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		return session;
	}
}

/* FLOW ->
 * For data persistent operation Create , Update and delete 
 * 1. you need a Session.
 * 2. begin the transaction
 * 3. perform operation like save() , persist() , update()
 * 4. get the transaction and commit it to permanent store the data. 
 */
