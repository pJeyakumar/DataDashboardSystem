package httpTest;

import java.util.ArrayList;

/*
 * NAMES: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-03-30
 * DESCRIPTION: Class that will be used to store the arrays of data from the JSON file that was fetched from the world bank
 */
public class Data
{	
	// Private instance variables
	private String typeA;
	private ArrayList<Double> firstSet;
	private ArrayList<Integer> secondSet;
	
	// Constructor class with dataType, dataArray1 and dataArray2 as parameters
	public Data(ArrayList<Double> data1)
	{
		this.firstSet = data1;
	}
	
	public Data(ArrayList<Double> data1, ArrayList<Integer> data2)
	{
		this.firstSet = data1;
		this.secondSet = data2;
	}
	// Setter method for firstSet var
	public void  setFirst(ArrayList<Double> data) 
	{
		this.firstSet = data;
	}
	// Setter method for secondSet var
	public void setSecond(ArrayList<Integer> data)
	{
		this.secondSet = data;
	}
	// Setter method for typeA var
	public void setTypeA(String type) 
	{
		this.typeA = type;
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
	// Getter method for tyoe
	public String getTypeA() 
	{
		return this.typeA;
	}	// Getter method for type
}

