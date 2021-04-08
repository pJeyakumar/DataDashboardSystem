package httpTest;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;

/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-07
 * DESCRIPTION: Abstract class for viewer objects 
 */
public abstract class Viewer 
{
	// Private instance variables
	private ViewerType viewerState = null;
	public String name;
	protected ChartPanel targetPanel;
	
	/**
	 * Constructor for viewer
	 * @param viewer The ViewerType of the viewer to be created 
	 */
	public Viewer(ViewerType viewer) 
	{
		this.viewerState = viewer;
	}
	
	/**
	 * Used to retrieve the type of this viewer object
	 * @return The ViewerType of this Viewer object
	 */
	public ViewerType getType() 
	{
		return this.viewerState;
	}
	
	/**
	 * Used to set the target display panel stored in this viewer object
	 * @param The ChartPanel object to be used as output for this viewer
	 */
	public void setPanel(ChartPanel target) {
		targetPanel = target;
	}
	
	
	/**
	 * Core method : Method to display / render the final output on the saved ChartPanel object
	 * @param data An ArrayList of type double holding the final data
	 * @param years An ArrayList of integers holding x axis data
	 * @param dataNames Names of the data series 
	 * @param axisNames Titles for the axes 
	 * @param analysisID Title of the whole plot and this current analysis
	 */
	protected abstract void display(ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID);
	
}
