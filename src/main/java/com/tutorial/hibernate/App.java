package com.tutorial.hibernate;

import com.tutorial.hibernate.entity.Emp;
import com.tutorial.hibernate.repo.Repository;

public class App {
  public static void main(String[] args) {
    
	  Repository repo = new Repository();
	  
	  /* ================== CRUD Using Hibernate(ORM)======================*/
	  
	  //Insert
	  Emp emp = new Emp("Sanskar",20000);
	  repo.addEmp(emp); 
	  
	  //Retrieve
	  Emp searchEmp = repo.getEmp(12);
	  System.out.print("Search Emp: "+searchEmp);
	  
	  //Update
	  if(searchEmp != null) {
		  searchEmp.setSalary(75000); //update salary
		  repo.updateEmp(searchEmp);
	  }
	  
	  //Delete
	  if(emp != null) {
		  emp.setId(1);
		  repo.deleteEmp(emp);
	  }
	  
	  //Get all data
	  System.out.println("EmpRecords:\n"+repo.getAllEmp());
	  
	  //Persist
	  Emp persistEmp = new Emp("Patidar",15000);
	  repo.persistObject(persistEmp);
  }
}
