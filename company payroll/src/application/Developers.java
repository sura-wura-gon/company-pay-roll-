package application;

import java.time.LocalDate;
import java.util.Date;

public class Developers extends Employees{
	
	 enum Category {junior, mid, senior};
	 private double salary;
	 private String roleTitle;
	 private String section;
	 
	 //default constructor
	Developers() 
	{ 
		super();
		this.salary = 0;
		this.roleTitle = "";
		this.section = "";
	}
	
	//parameterized constructor
	Developers(String name, String gender, LocalDate dateBirth, int ID, LocalDate dateHired, String position, double salary, String roleTitle, String section)
	{
		super(name, gender, dateBirth, ID, dateHired, position);
		this.salary = salary;
		this.roleTitle = roleTitle;
		this.section = section;
	}
	 
	 //getters and setters
	 public double getSalary() {return salary;}
	 public void setSalary(double salary) {this.salary = salary;}
	 public String getRoleTitle() {return roleTitle;}
	 public void setRoleTitle(String roleTitle) {this.roleTitle = roleTitle;}
	 public String getSection() {return section;}
	 public void setSection(String section) {this.section = section;}
 
	//convert to string
	public String toString() 
	{
		return super.toString();
	}
	
	//pay raise calculation
	public void payRaise() throws ValueLargeException 
	{
		
		if(this.section == "Junior" )
		{
			if(salary<2000)
			{
				this.salary = (this.salary*0.03) + this.salary;
			}else 
			{
				throw new ValueLargeException();
			}
	
		}else if(this.section == "Mid")
		{
			if(salary<4000)
			{
				this.salary = (this.salary*0.03) + this.salary;
			}else
			{
				throw new ValueLargeException();
			}
		}else if (this.section == "Senior")
		{
			this.salary = (this.salary*0.03) + this.salary;
		}
		
	}
	
	//reset hrs empty
	public void reset()
	{};
	
	public double pay()
	{
		return this.salary;
	}
	
	public String display()
	{
		String s = "\nRole Title:" + this.roleTitle  + "\nSection:" + this.section;
		return s;
	}
	
	public String print()
	{
		String s = " Role Title:" + this.roleTitle  + " Section:" + this.section;
		return s;
	}
		
		
		
		
	
}//class end