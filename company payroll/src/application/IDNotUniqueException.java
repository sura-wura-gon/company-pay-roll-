package application;

public class IDNotUniqueException extends Exception{
	
	private int numb;
	
	public IDNotUniqueException(int numb)
	{
		this.numb = numb;
	}
	
	public String toString()
	{
		return "number " + this.numb + " is not unique ID please enter again";
	}

}
