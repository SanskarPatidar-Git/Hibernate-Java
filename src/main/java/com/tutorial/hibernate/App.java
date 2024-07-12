package com.tutorial.hibernate;

public class App {
  public static void main(String[] args) {
    
	  Repository repo = new Repository();
	  
	  //Insert data through ORM.
	  Emp emp = new Emp("Sanskar",35000);
	  repo.addEmp(emp); 
  }
}
