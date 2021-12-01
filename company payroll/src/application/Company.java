package application;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Company {
	
	private double balance;
	ArrayList<Employees> employees = new ArrayList<Employees>();
 
	// default constructor
	Company()
	{
		this.balance = 0; 	
	}
 
	// parameterized constructor
	Company(double balance, ArrayList<Employees> employees)
	{
	 this.balance = balance;
	}

	//getters and setters
	public double getBalance() {return balance;}
	public void setBalance(double balance) {this.balance = balance;}
	
	//add employee
	public void addEmployee (Employees emp) throws IDNotUniqueException, NegativeNumberExcepion
	{
		for(int i=0; i<employees.size(); i++)
		{
			if(employees.get(i).getID() == emp.getID())
			{
				throw new IDNotUniqueException(emp.getID());
			}
			if(emp.getID() < 0)
			{
				throw new NegativeNumberExcepion("ID");
			}
			if(emp.getSalary() < 0)
			{
				throw new NegativeNumberExcepion("Salary");
			}
		}	
		employees.add(emp);
	}

	//remove employee
	public void removeEmployee(Employees emp)
	{
		for(int i = 0; i<this.employees.size(); i++)
		{
			if(this.employees.get(i) == emp)
			{
				this.employees.remove(i); 
			}
		} 
	}
	
	//payroll calculation 
	public void payroll() throws NegativeBalanceException
	{
		double amount =0;
		for(int i = 0; i<employees.size(); i++)
		{
			 amount = amount + employees.get(i).pay(); 
		}
		
		if(this.balance<amount)
		{
			throw new NegativeBalanceException();
		}else
		{
			this.balance = this.balance - amount;
		}
		
	} 
	
	//pay raise
	public void promotion(Employees emp) throws ValueLargeException
	{
		for(int i = 0; i<employees.size(); i++)
		{
			if(employees.get(i) == emp) 
			{
				employees.get(i).payRaise();
			}	
		}
	}
	
	//top balance
	public void topUp(double amount)
	{
		balance = balance + amount;
	}
	
}//class end