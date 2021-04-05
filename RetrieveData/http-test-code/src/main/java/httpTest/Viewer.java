package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: Abstract class for describing the methods and variables the subclasses will inherit
 */
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
	
	private JPanel targetPanel;
	
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
	
	public void setPanel(JPanel target) {
		targetPanel = target;
	}
	
	
	// Subclass-level displaying
	protected abstract void display(JPanel plotArea, ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID);
	
}
