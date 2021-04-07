package httpTest;

import javax.naming.spi.DirStateFactory.Result;

/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-07
 * DESCRIPTION: Factory creator class for generating appropriate viewer objects 
 */

public class ViewerCreator 
{
	
	/**
	 * Constructor for viewerCreator class
	 * @param viewer The ViewerType of the viewer to be created 
	 */
	public Viewer createViewer(ViewerType viewer) 
	{
		// Produce the new viewer object
		Viewer newViewer = null;
		switch(viewer)
		{
		
		case BARGRAPH:
			newViewer = new BarGraph();
			break;
		case LINEGRAPH:
			newViewer = new LineGraph();
			break;
		case SCATTERPLOT:
			newViewer = new ScatterPlot();
			break;
		case PIECHART:
			newViewer = new PieChart();
			break;
		case REPORT:
			newViewer = new Report();
			break;
		default:
			// error message
			break;		
		}
		
		// Return the new viewer
		return newViewer;
	}
}
