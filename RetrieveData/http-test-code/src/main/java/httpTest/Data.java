package httpTest;

import java.util.ArrayList;

/*
 * NAMES: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-03-30
 * DESCRIPTION: Class that will be used to store the arrays of data from the JSON file that was fetched from the world bank
 */
public class Data<A,B>
{	
	// Private instance variables
	private String typeA;
	private String typeB;
	private ArrayList<A> firstSet;
	private ArrayList<B> secondSet;
	
	// Constructor class with dataType, dataArray1 and dataArray2 as parameters
	public Data(ArrayList<A> data1, ArrayList<B> data2)
	{
		this.firstSet = data1;
		this.secondSet = data2;
	}
	// Setter method for firstSet var
	public void  setFirst(ArrayList<A> data) 
	{
		this.firstSet = data;
	}
	// Setter method for secondSet var
	public void setSecond(ArrayList<B> data)
	{
		this.secondSet = data;
	}
	// Setter method for typeA var
	public void setTypeA(String type) 
	{
		this.typeA = type;
	}	// Setter method for typeB var
	public void setTypeB(String type) 
	{
		this.typeB = type;
	}
	// Getter method for firstSet
	public ArrayList<A> getFirst() 
	{
		return this.firstSet;
	}
	// Getter method for secondSet
	public ArrayList<B> getSecond() 
	{
		return this.secondSet;
	}
	// Getter method for tyoe
	public String getTypeA() 
	{
		return this.typeA;
	}	// Getter method for tyoe
	public String getTypeB() 
	{
		return this.typeB;
	}
}

