package com.tutorial.hibernate;

public class App {
  public static void main(String[] args) {
    
	  Repository repo = new Repository();
	  
	  //Insert data through ORM.
	  Emp emp = new Emp("Sanskar",20000);
	  repo.addEmp(emp); 
	  
	  //Retrieve data
	  Emp searchEmp = repo.getEmp(10);
	  System.out.print("Search Emp: "+searchEmp);
  }
}
