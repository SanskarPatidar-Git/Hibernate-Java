package com.tutorial.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.tutorial.hibernate.entity.Emp;
import com.tutorial.hibernate.entity.Order;
import com.tutorial.hibernate.entity.User;
import com.tutorial.hibernate.repo.AssociationRepo;
import com.tutorial.hibernate.repo.Repository;

public class App {
  public static void main(String[] args) {
    
	  Repository repo = new Repository();
	  
	  /* ====================== CRUD Using Hibernate(ORM)======================*/
	  
	  //Insert
	  Emp emp = new Emp("Sanskar",20000);
	  repo.addEmp(emp); 
	  
	  //Retrieve
	  Emp searchEmp = repo.getEmp(1);
	  System.out.print("Search Emp: "+searchEmp);
	  
	  //Update
	  if(searchEmp != null) {
		  searchEmp.setSalary(75000); //update salary
		  repo.updateEmp(searchEmp);
	  }
	  
	  //Delete
	  if(emp != null) {
		  emp.setId(1);
		  //repo.deleteEmp(emp);
	  }
	  
	  //Get all data
	  System.out.println("EmpRecords:\n"+repo.getAllEmp());
	  
	  //Persist
	  Emp persistEmp = new Emp("Patidar",15000);
	  repo.persistObject(persistEmp);
	  
	  
	  /* ================= Association Mapping (Relation between tables)=================*/
	  
	  AssociationRepo mapRepo = new AssociationRepo();
	  
	  /* OneToMany & ManyToOne
	   * A user has many orders and many orders done by single user.
	   * Orders contains foreign key as primary key of User.
	   */
	  User user = new User("Sanskar");
	  
	  List<Order> orderList = new ArrayList<Order>();
	  orderList.add(new Order("Poha",20,user));
	  orderList.add(new Order("Samosa",20,user));
	  orderList.add(new Order("Jalebi",60,user));
	  
	  user.setOrders(orderList);
	  
	  mapRepo.addUser(user);
	  
	  //Retrieve
	  /*
	   * fetch = FetchType.EAGER & LAZY
	   * EAGER -> Get the data of mapped class when loading the data of parent
	   * - When the user data is fetched also fetch the orders data mapped with that user at that time.
	   * LAZY -> Get the data when it is accessed for first time.
	   * - When you use getOrders() in loop at that time hibernate fetch the data of orders.
	   * TRY : change fetch type in User entity and check the console. While LAZY it will load data later.
	   * 
	   *  NOTE: if you are using toString() or equals() in a parent entity while retrieve the data 
	   *        it will give StackOverFlow Error.
	   */
	  User searchUser = mapRepo.getUser(1);
	  System.out.println("SearchUser: "+"Id: "+searchUser.getId()+" Name: "+searchUser.getName());
	  for(Order order : searchUser.getOrders())
		  System.out.println("Name: "+order.getName()+" Price: "+order.getPrice());
	  
	  /*
	   * CASCADE -
	   * All the operations like create,update,delete are performed on any entity it will also
	   * changed in mapped entity.
	   * If CascadeType.ALL -> Perform all operations
	   * or cascade = { CascadeType.CREATE , CascadeType.UPDATE } for specific operations.
	   */
	  
  }
}
