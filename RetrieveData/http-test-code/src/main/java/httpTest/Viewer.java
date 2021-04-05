package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: Abstract class for describing the methods and variables the subclasses will inherit
 */
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-01
 * DESCRIPTION:
 */
public abstract class Viewer 
{
	// private instance variables
	private ViewerType viewerState = null;
	
	public String name;
	
	protected ChartPanel targetPanel;
	
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
	
	public void setPanel(ChartPanel target) {
		targetPanel = target;
	}
	
	public String getName() {
		return name;
	}
	
	// Subclass-level displaying
	protected abstract void display(ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID);
	
}
