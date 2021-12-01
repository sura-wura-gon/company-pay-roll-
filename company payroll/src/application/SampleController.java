package application;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class SampleController 
{
	@FXML private ChoiceBox<String> option;
	@FXML private TextField name;
	@FXML private TextField id;
    @FXML private ChoiceBox<String> gender;
	@FXML private DatePicker dateBirth;
	@FXML private DatePicker dateHired;
	@FXML private TextField salary;
	@FXML private TextField budget;
	@FXML private TextField budgetCap;
    @FXML private ListView<Employees> empList;
    @FXML private ChoiceBox<String> section;
    @FXML private Label salaryLabel;
    @FXML private Label budgetLabel;
    @FXML private Label capLabel;
    @FXML private Button empDetails;
    @FXML private Button empRemove;
    @FXML private Button empRaise;
    @FXML private Label balanceLabel;
    @FXML private Button topButton;
    
	
	//private ArrayList<Employees> employees;
	private Company c;
	
	
	//add employee action
	public void addEmployee(ActionEvent e) throws EmptyInputException, NegativeBalanceException
	{
		try {
		String emptype = option.getValue();
		if(emptype.equals("Manager"))
		{
			Managers m = new Managers();
			
			m.setPosition("Manager");
			m.setName(name.getText());
			m.setID(Integer.parseInt(id.getText())) ;
			m.setGender(gender.getValue()) ;
			m.setDateBirth(dateBirth.getValue()) ;
			m.setDateHired(dateHired.getValue()); 
			
			m.setSalary(Double.parseDouble(salary.getText())); 
			m.setSpent(Double.parseDouble(budget.getText())); 
			m.setBudget(Double.parseDouble(budgetCap.getText())) ;
			
			if(name.getText().equals(""))
			{
				throw new EmptyInputException();
			}
			if(dateBirth.getValue() == null || dateHired.getValue() == null)
			{
				throw new EmptyInputException();
			}
			if(Double.parseDouble(budget.getText()) > Double.parseDouble(budgetCap.getText()))
			{
				throw new NegativeBalanceException();
			}
			
			c.addEmployee(m);
			
			//employees.add(m);
			empList.setItems(FXCollections.observableList(c.employees));
		}else if(emptype.equals("Developer"))
		{
			Developers d = new Developers();
			
			d.setPosition("Developer");
			d.setName(name.getText()); 
			d.setID(Integer.parseInt(id.getText())); 
			d.setGender(gender.getValue());
			d.setDateBirth(dateBirth.getValue());
			d.setDateHired(dateHired.getValue()); 
			
			d.setSalary(Double.parseDouble(salary.getText())); 
			d.setRoleTitle(budget.getText()); 
			d.setSection(section.getValue());
			
			if(name.getText().equals(""))
			{
				throw new EmptyInputException();
			}
			if(dateBirth.getValue() == null || dateHired.getValue() == null)
			{
				throw new EmptyInputException();
			}
			
			c.addEmployee(d);
			empList.setItems(FXCollections.observableList(c.employees));	
		}else if(emptype.equals("Support"))
		{
			customerSupport cs = new customerSupport();
			
			cs.setPosition("Support");
			cs.setName(name.getText()); 
			cs.setID(Integer.parseInt(id.getText())); 
			cs.setGender(gender.getValue()); 
			cs.setDateBirth(dateBirth.getValue()); 
			cs.setDateHired(dateHired.getValue()); 
			
			cs.setSalary(Double.parseDouble(salary.getText())); 
			cs.setNumHours(Integer.parseInt(budget.getText()));
			
			if(name.getText().equals(""))
			{
				throw new EmptyInputException();
			}
			if(dateBirth.getValue() == null || dateHired.getValue() == null)
			{
				throw new EmptyInputException();
			}
			
			c.addEmployee(cs);
			empList.setItems(FXCollections.observableList(c.employees));	
		}
		}catch(NegativeNumberExcepion ex)
		{
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText(ex.toString());
	        info.showAndWait();
		}catch(IDNotUniqueException ex)
		{
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText(ex.toString());
	        info.showAndWait();
		}catch(EmptyInputException ex)
		{
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText(ex.toString());
	        info.showAndWait();
		}catch(java.lang.NumberFormatException ex)
		{
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText("Please use numerical values in numerical fields");
	        info.showAndWait();
		}catch(NegativeBalanceException ex)
		{
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText("Budget cannot exceed budget cap");
	        info.showAndWait();
		}
		//set values to null once employee is added
		name.setText("");
		id.setText("");
		dateBirth.setValue(null);
		dateHired.setValue(null);
		salary.setText("");
		budget.setText("");
		budgetCap.setText("");
	}
	
	//change employee type action
	public void changeType(ActionEvent e)
	{
		String emptype = option.getValue();
		
		if(emptype.equals("Manager"))
		{
			salary.setVisible(true);
			budget.setVisible(true);
			budgetCap.setVisible(true);
			section.setVisible(false);
			salaryLabel.setText("Salary");
			budgetLabel.setText("Budget");
			capLabel.setText("Budet cap");
			
		}else if(emptype.equals("Developer"))
		{
			salary.setVisible(true);
			budget.setVisible(true);
			budgetCap.setVisible(false);
			section.setVisible(true);
			salaryLabel.setText("Salary");
			budgetLabel.setText("Role title");
			capLabel.setText("Section");

		}else if(emptype.equals("Support"))
		{
			salary.setVisible(true);
			budget.setVisible(true);
			budgetCap.setVisible(false);
			section.setVisible(false);
			salaryLabel.setText("Wage");
			budgetLabel.setText("Hours");
			capLabel.setText("");
		}
	}
	
    //show employee action
    public void showEmployee(ActionEvent event)
    {
    	try {
        Employees emp1 = (Employees) empList.getSelectionModel().getSelectedItem();
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Employee Details");
        
        info.setContentText("Name:" + emp1.getName() + "\nGender:" + emp1.getGender() + "\nID:" + emp1.getID() + "\nDate of Birth:" + emp1.getDateBirth() + "\nDateHired:" + emp1.getDateHired() + "\nPosition:" + emp1.getPosition() + "\nSalary" + emp1.getSalary() + emp1.display());
       
        info.showAndWait();

        empList.setItems(FXCollections.observableList(c.employees));
    	}catch(java.lang.NullPointerException ex)
    	{
    		Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText("You have not made any selection from the list");
	        info.showAndWait();
    	}
    }
    
    //remove employee action
    public void removeEmployee(ActionEvent actionevent)
    {
    	try {
    	Employees emp1 = (Employees) empList.getSelectionModel().getSelectedItem();
    	if(emp1 == null)
    	{
    		throw new NullPointerException();
    	}
    	c.removeEmployee(emp1);
    	Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Employee Details");
        info.setContentText(emp1 +" has been successfully removed");
        info.showAndWait();
        empList.setItems(FXCollections.observableList(c.employees));
    	}catch(java.lang.NullPointerException ex)
    	{
    		Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText("You have not made any selection from the list");
	        info.showAndWait();
    	}
    }
    
    //pay raise employee action
	public void raiseEmployee(ActionEvent actionevent) throws ValueLargeException
	{
		try {
		Employees emp1 = (Employees) empList.getSelectionModel().getSelectedItem();
		if(emp1 == null)
    	{
    		throw new NullPointerException();
    	}
		
		c.promotion(emp1);
		
		Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Employee Details");
        info.setContentText(emp1 +" has been successfully promoted");
        info.showAndWait();
		
        empList.setItems(FXCollections.observableList(c.employees));
		}catch(java.lang.NullPointerException ex)
    	{
    		Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText("You have not made any selection from the list");
	        info.showAndWait();
    	}catch(ValueLargeException ex)
		{
    		Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText(ex.toString());
	        info.showAndWait();
		}
	}
	
	//pay employees action
	public void payEmployees(ActionEvent actionevent)
	{
		try {
		c.payroll();
		String newAmount = c.getBalance() + "";
		balanceLabel.setText(newAmount);
		}catch(NegativeBalanceException ex)
		{
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText(ex.toString());
	        info.showAndWait();
		}
	} 
	
	//top balance action
	public void topBalance(ActionEvent actionevent)
	{
		try {
		TextInputDialog td = new TextInputDialog("enter value here"); 
		td.setHeaderText("Amount to top up");
	    Optional<String> answer = td.showAndWait();
	    c.topUp(Double.parseDouble(answer.get())); 
		String bal = c.getBalance() + "";
		balanceLabel.setText(bal);
		}catch(java.lang.NumberFormatException ex)
		{
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText("Please use numerical values in numerical fields");
	        info.showAndWait();
		}
	} 
	
	//finish action
	public void finish(ActionEvent actionevent)
	{
		try {
		FileWriter write = new FileWriter("employeeDetails.txt");
		
		for(int i = 0; i<c.employees.size(); i++)
		{

			String es = "Name:" + c.employees.get(i).getName() + " Gender:" + c.employees.get(i).getGender() + " ID:" + c.employees.get(i).getID() + " Date of Birth:" + c.employees.get(i).getDateBirth() + " DateHired:" + c.employees.get(i).getDateHired() + " Position:" +c.employees.get(i).getPosition() + " Salary" + c.employees.get(i).getPosition() + c.employees.get(i).print();
			write.write(es + "\n");
		}
		write.close();
		
		Platform.exit();
		}catch (IOException e)
		{
			System.out.println("file could not be written");
		}
		
		
	} 
	
	//initializer
	public void initialize()
	{
		option.setValue("Manager");
		option.setItems(FXCollections.observableArrayList("Manager", "Developer", "Support"));
		gender.setValue("Male");
		gender.setItems(FXCollections.observableArrayList("Male", "Female"));
		section.setItems(FXCollections.observableArrayList("Junior", "Mid", "Senior"));
		section.setValue("Junior");
		//employees = new ArrayList<Employees>();
		c = new Company();
		c.setBalance(Double.parseDouble(balanceLabel.getText()));
		
		readFile();
		
		empList.setItems(FXCollections.observableList(c.employees));
			
	} 
	
	public void readFile()
	{
		try 
		{
			FileReader myFile = new FileReader("employeeDetails.txt");
			Scanner myscan = new Scanner(myFile);
			
			//read file and store employees into array
			while(myscan.hasNextLine())
			{
				String employee = myscan.nextLine();
				String[] split = employee.split(" ");
			
				if(split[0].equals("Manager"))
				{
					Managers manager = new Managers();
					
					manager.setPosition("Manager");
					//name
					manager.setName(split[1]);
						
					//gender
					if(split[2].equals("male"))
					{
						manager.setGender("Male");;
					}else
					{
						manager.setGender("Female");
					}
						
					//date of birth
					int year = Integer.parseInt(split[3]);
					int month = Integer.parseInt(split[4]);
					int day = Integer.parseInt(split[5]);
					String Month = month + "";
					String Day = day + "";
					if(month < 10)
					{
						Month = "0" + Month;	
					}
					
					if(day < 10)
					{
						Day = "0" + Day;	
					}
					
					String combine = year +  "-" + Month + "-" + Day; 
					LocalDate dBirth = LocalDate.parse(combine);
					manager.setDateBirth(dBirth);
						
					// id
					int id = Integer.parseInt(split[6]);
					manager.setID(id);
						
					//date hired
					String year1 = split[7];
					String month1 = split[8];
					String day1 = split[9];
					
					if(Integer.parseInt(month1) < 10)
					{
						month1 = "0" + month1;	
					}
					
					if(Integer.parseInt(day1) < 10)
					{
						day1 = "0" + day1;	
					}
					
					String combine1 = year1 + "-" + month1 + "-" + day1; 
					LocalDate dHired = LocalDate.parse(combine1);
					
					manager.setDateHired(dHired);
						
					//salary
					double sal = Double.parseDouble(split[10]);
					manager.setSalary(sal);
						
					//spent
					double sp = Double.parseDouble(split[11]);
					manager.setSpent(sp);
						
					//budget
					double bud = Double.parseDouble(split[12]);
					manager.setBudget(bud);
						
					//add to employee array	
					c.employees.add(manager);
						
				}else if(split[0].equals("Developer"))
				{
					Developers developer = new Developers();
					
					developer.setPosition("Developer");
					
					//name
					developer.setName(split[1]);
						
					//gender
					if(split[2].equals("male"))
					{
						developer.setGender("Male");
					}else
					{
						developer.setGender("Female");
					}
						
					//date of birth
					String year = split[3];
					String month = split[4];
					String day = split[5];
					
					if(Integer.parseInt(month) < 10)
					{
						month = "0" + month;	
					}
					
					if(Integer.parseInt(day) < 10)
					{
						day = "0" + day;	
					}
					
					String combine = year +  "-" + month + "-" + day; 
					LocalDate dBirth = LocalDate.parse(combine);
					developer.setDateBirth(dBirth);
						
					// id
					int id = Integer.parseInt(split[6]);
					developer.setID(id);
						
					//date hired
					String year1 = split[7];
					String month1 = split[8];
					String day1 = split[9];
					
					if(Integer.parseInt(month1) < 10)
					{
						month1 = "0" + month1;	
					}
					
					if(Integer.parseInt(day1) < 10)
					{
						day1 = "0" + day1;	
					}
					
					String combine1 = year1 + "-" + month1 + "-" + day1; 
					LocalDate dHired = LocalDate.parse(combine1);
					developer.setDateHired(dHired);
					
					//salary
					double sal = Double.parseDouble(split[10]);
					developer.setSalary(sal);
					
					//category
					if(split[11].equals("senior"))
					{
						developer.setSection("Senior");
					}else if(split[11].equals("junior"))
					{
						developer.setSection("Junior");
					}else if(split[11].equals("mid-level"))
					{
						developer.setSection("Mid");
					}
					
					//role title
					developer.setRoleTitle(split[12]);	
					
					//add to employee array
					c.employees.add(developer);
					
				}else if(split[0].equals("Support"))
				{
					customerSupport cs = new customerSupport();
					
					cs.setPosition("Support");
					
					//name
					cs.setName(split[1]);
						
					//gender
					if(split[2].equals("male"))
					{
						cs.setGender("Male");
					}else
					{
						cs.setGender("Female");
					}
						
					//date of birth
					String year = split[3];
					String month = split[4];
					String day = split[5];
					
					if(Integer.parseInt(month) < 10)
					{
						month = "0" + month;	
					}
					
					if(Integer.parseInt(day) < 10)
					{
						day = "0" + day;	
					}
					
					String combine = year +  "-" + month + "-" + day; 
					LocalDate dBirth = LocalDate.parse(combine);
					cs.setDateBirth(dBirth);
						
					// id
					int id = Integer.parseInt(split[6]);
					cs.setID(id);
						
					//date hired
					String year1 = split[7];
					String month1 = split[8];
					String day1 = split[9];
					
					if(Integer.parseInt(month1) < 10)
					{
						month1 = "0" + month1;	
					}
					
					if(Integer.parseInt(day1) < 10)
					{
						day1 = "0" + day1;	
					}
					
					String combine1 = year1 + "-" + month1 + "-" + day1; 
					LocalDate dHired = LocalDate.parse(combine1);
					cs.setDateHired(dHired);
					
					//wage
					double wage = Double.parseDouble(split[10]);
					cs.setSalary(wage);
					
					//hours
					int hrs = Integer.parseInt(split[11]);
					cs.setNumHours(hrs);
					
					//add to employee array
					c.employees.add(cs);
				}//else if end
				
			}//while loop end
			
			myFile.close();
			
			
		}catch (IOException e)
		{
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("ERROR!");
	        info.setContentText("File not found");
	        info.showAndWait();
		}
	}
	
	
}
