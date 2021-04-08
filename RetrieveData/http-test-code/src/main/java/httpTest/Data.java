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
	
	/* DESCRIPTION: Constructor method with data1 as a parameter
	 * INPUT: ArrayList<Double>
	 * OUTPUT: N/A
	 */
	public Data(ArrayList<Double> data1)
	{
		this.firstSet = data1;
	}
	
	/* DESCRIPTION: Second Constructor method with data1 AND data2 as parameters
	 * INPUT: ArrayList<Double>, ArrayList<Integer> 
	 * OUTPUT: N/A
	 */
	public Data(ArrayList<Double> data1, ArrayList<Integer> data2)
	{
		this.firstSet = data1;
		this.secondSet = data2;
	}
	
	/* DESCRIPTION: Third Constructor method with data1 AND data2 AND type as parameters
	 * INPUT: ArrayList<Double>, ArrayList<Integer>, String
	 * OUTPUT: N/A
	 */
	public Data(ArrayList<Double> data1, ArrayList<Integer> data2, String type)
	{
		this.firstSet = data1;
		this.secondSet = data2;
		this.aType = type;
	}
	
	/* DESCRIPTION: Setter method for firstSet variable
	 * INPUT: ArrayList<Double>
	 * OUTPUT: N/A
	 */
	public void  setFirst(ArrayList<Double> data) 
	{
		this.firstSet = data;
	}
	
	/* DESCRIPTION:Setter method for secondSet variable
	 * INPUT: ArrayList<Integer>
	 * OUTPUT: N/A
	 */
	public void setSecond(ArrayList<Integer> data)
	{
		this.secondSet = data;
	} 
	
	/* DESCRIPTION: Setter method for aType variable
	 * INPUT: String
	 * OUTPUT: N/A
	 */
	public void setTypeA(String type) 
	{
		this.aType = type;
	}
	
	/* DESCRIPTION: Getter method for firstSet
	 * INPUT: N/A
	 * OUTPUT: ArrayList<Double>
	 */
	public ArrayList<Double> getFirst() 
	{
		return this.firstSet;
	}
	
	/* DESCRIPTION: Getter method for secondSet
	 * INPUT: N/A
	 * OUTPUT: ArrayList<Integer>
	 */
	public ArrayList<Integer> getSecond() 
	{
		return this.secondSet;
	}
	
	/* DESCRIPTION: Getter method for type
	 * INPUT: N/A
	 * OUTPUT: String
	 */
	public String getTypeA() 
	{
		return this.aType;
	}
}

