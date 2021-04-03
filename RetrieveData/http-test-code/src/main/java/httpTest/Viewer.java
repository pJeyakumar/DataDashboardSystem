package httpTest;

import java.util.ArrayList;

import javax.swing.JPanel;

/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-01
 * DESCRIPTION:
 */
public abstract class Viewer 
{
	// private instance variables
	private ViewerType viewerState = null;
	
	// constructor class with ViewerType for parameter
	public Viewer(ViewerType viewer) 
	{
		this.viewerState = viewer;
	}
	
	// Getter method for viewerState(ViewerType)
	public ViewerType getType() 
	{
		return this.viewerState;
	}
	
	// Subclass-level displaying
	protected abstract void display(JPanel plotArea, ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID);
	
}
