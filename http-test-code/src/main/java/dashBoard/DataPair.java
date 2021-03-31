package httpTest;
package dashBoard;

import java.util.ArrayList;
/*
 * NAMES: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-03-30
 * DESCRIPTION: 
 */
public interface DataPair <A,B>
{
	// Setter method for firstSet var
	public void  setFirst(ArrayList<A> data); 
	// Setter method for secondSet var
	public void setSecond(ArrayList<B> data);
	// Setter method for typeA var
	public void setTypeA(String type); 
	// Setter method for typeB var
	public void setTypeB(String type); 
	// Getter method for firstSet
	public ArrayList<A> getFirst(); 
	// Getter method for secondSet
	public ArrayList<B> getSecond(); 
	// Getter method for tyoe
	public String getTypeA(); 
	// Getter method for tyoe
	public String getTypeB(); 
}
