package application;

public class EmptyInputException extends Exception{
		
	public EmptyInputException() { }
	
	public String toString()
	{
		return "please dont leave any fields empty";
	}
	
}
