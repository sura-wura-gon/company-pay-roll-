package application;

import java.time.LocalDate;
import java.util.Date;

public class customerSupport extends Employees{

	double salary;
	int numHours;
	
	//default constructor
	customerSupport()
	{
		salary = 0.0;
		numHours = 0;
	}
	
	//parameterized constructor
	customerSupport(String name, String gender, LocalDate dateBirth, int ID, LocalDate dateHired, String position, double salary, int numHours)
	{
		super(name, gender, dateBirth, ID, dateHired, position);
		this.salary = salary;
		this.numHours = numHours;
	}
	
	//getters and setters
	public double getSalary() {return salary;}
	public void setSalary(double salary) {this.salary = salary;}
	public int getNumHours() {return numHours;}
	public void setNumHours(int numHours) {this.numHours = numHours;}

	// convert to string
	@Override
	public String toString() 
	{
		return super.toString();
	}
	
	//paid by hour calculation
	public void overtime () 
	{
		 double fullWage;
		 int extraHours = 48 - numHours;
		 if (extraHours <= 0) {
			 fullWage = numHours*salary;
		 } else {
			fullWage = ((0.25*salary)*extraHours) + (numHours*salary);
		 }
	}
	
	

	//pay raise calculation
	public void payRaise() 
	{
		double payR = salary + (salary*0.02);
	}
	
	//reset numb hrs worked
	public void reset()
	{
		numHours = 0;
	}
	
    public double pay()
    {   
    	if(this.numHours <= 48)
            return this.numHours*this.salary;
        return 48 * this.salary + ((this.numHours-48)*this.salary/4);
    }
    
	public String display()
	{
		String s = "\nBudget:" + this.numHours;
		return s;
	}
	
	public String print()
	{
		String s = " Budget:" + this.numHours;
		return s;
	}
 
		
	
	
}//class end