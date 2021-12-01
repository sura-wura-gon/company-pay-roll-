package application;

public class NegativeBalanceException extends Exception{
	
	public NegativeBalanceException() {}
	
	public String toString()
	{
		return "Not enough company balance to pay employees";
	}

}
