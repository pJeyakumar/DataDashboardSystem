package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-01
 * DESCRIPTION: Class responsible for creating a Viewer object based on the ViewerType that was given
 */
import javax.naming.spi.DirStateFactory.Result;

public class ViewerCreator 
{
	public Viewer createViewer(ViewerType viewer) 
	{
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
		return newViewer;
	}
}
