package application;

import java.time.LocalDate;
import java.util.Date;

public class Managers extends Employees {

	private double salary;
	private double budget;
	private double spent;

	 
	 //default constructor
	
	 Managers()
	 {
		 super();
		 this.salary = 0;
		 this.budget = 0;
		 this.spent = 0;

	 } 
	 
	 //parameterized constructor
	 Managers(String name, String gender, LocalDate dateBirth, int ID, LocalDate dateHired, String position, double salary, double budget, double spent) throws NegativeBalanceException
	 {
		 super(name, gender, dateBirth, ID, dateHired, position);
		 this.salary = salary;
		 this.budget = budget;
		 this.spent = spent;
		 
		 if(spent>budget)
		 {
			 throw new NegativeBalanceException();
		 }
		
	 }


	 //getters and setters
	public double getSalary() {return salary;}
	public void setSalary(double salary) {this.salary = salary;}
	public double getBudget() {return budget;}
	public void setBudget(double budget) {this.budget = budget;}
	public double getSpent() {return spent;}
	public void setSpent(double spent) {this.spent = spent;} 

	
	
	//convert to string
	public String toString() 
	{
		return super.toString() ;
	}

	//pay raise calculation
	public void payRaise()
	{
	this.salary = (salary*0.05) + this.salary;
	this.budget = this.budget + 200.0;
	}
	
	//reset amount spent
	public void reset()
	{
		spent = 0;
	}
	
	public double pay()
	{
		double amount = this.salary + (this.budget - this.spent);
		return amount;
	}
	
	public String display()
	{
		String s = "\nBudget:" + this.budget  + "\nSpent:" + this.spent;
		return s;
	}
	
	public String print()
	{
		String s = " Budget:" + this.budget  + " Spent:" + this.spent;
		return s;
	}
	
}// class end
