package application;

public class NegativeNumberExcepion extends Exception{
	
	private String numb;
	
	public NegativeNumberExcepion(String numb)
	{
		this.numb = numb;
	}
	
	public String toString()
	{
		return "Value of " + this.numb + " cannot be negative";
	}

}
