package httpTest;

import java.util.ArrayList;

/*
 * NAMES: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-03-31
 * DESCRIPTION: Class that will be used to store the arrays of data from the JSON file that was fetched from the world bank
 */
public class Data
{	
	// Private instance variables
	private String aType;					// analysis
	private ArrayList<Double> firstSet;		// values
	private ArrayList<Integer> secondSet;	// years
	
	// Constructor class with data1 as a parameter
	public Data(ArrayList<Double> data1)
	{
		this.firstSet = data1;
	}
	// Second Constructor class with 
	public Data(ArrayList<Double> data1, ArrayList<Integer> data2)
	{
		this.firstSet = data1;
		this.secondSet = data2;

	}
	
	public Data(ArrayList<Double> data1, ArrayList<Integer> data2, String type)
	{
		this.firstSet = data1;
		this.secondSet = data2;
		this.aType = type;
	}
	
	// Setter method for firstSet variable
	public void  setFirst(ArrayList<Double> data) 
	{
		this.firstSet = data;
	}
	// Setter method for secondSet variable
	public void setSecond(ArrayList<Integer> data)
	{
		this.secondSet = data;
	}
	// Setter method for aType variable
	public void setTypeA(String type) 
	{
		this.aType = type;
	}
	// Getter method for firstSet
	public ArrayList<Double> getFirst() 
	{
		return this.firstSet;
	}
	// Getter method for secondSet
	public ArrayList<Integer> getSecond() 
	{
		return this.secondSet;
	}
	// Getter method for type
	public String getTypeA() 
	{
		return this.aType;
	}
}

