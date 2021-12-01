package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public abstract class Employees {
	
	private String name;
	private String gender; 
	private LocalDate dateBirth;
	private int ID;
	private LocalDate dateHired;
	private String position = "";
	
	
	//default constructors
	
	Employees()
	{
		this.name = "";
		this.gender = "";
		this.dateBirth = LocalDate.now();
		this.ID = 0;
		this.dateHired = LocalDate.now();
		this.position = "";
		
	} 
	
	//parameterized constructors
	Employees(String name, String gender, LocalDate dateBirth, int ID, LocalDate dateHired, String position)
	{
		this.name = name;
		this.gender = gender;
		this.dateBirth = dateBirth;
		this.ID = ID;
		this.dateHired = dateHired;
		this.position = position;
	}
	
	
	
	//getters and setters
	public String getName() {return name;}
	public void setName (String name) {this.name = name;}
	public String getGender() {return gender;}
	public void setGender (String gender) {this.gender = gender;}
	public LocalDate getDateBirth() {return dateBirth;}
	public void setDateBirth (LocalDate dateBirth) {this.dateBirth = dateBirth;}
	public int getID () {return ID;}
	public void setID (int ID) {this.ID = ID;}
	public LocalDate getDateHired () {return dateHired;}
	public void setDateHired (LocalDate dateHired) {this.dateHired = dateHired;}
	public String getPosition() {return position;}
	public void setPosition(String position) {this.position = position;}
	
	
	//convert to string
	public String toString() 
	{
		return "[" + ID + "]" + name;
	}
	
	//abstract functions
	public abstract void payRaise() throws ValueLargeException;
	public abstract double getSalary();
	public abstract void reset();
	public abstract double pay();
	public abstract String display();
	public abstract String print();


}//class end
