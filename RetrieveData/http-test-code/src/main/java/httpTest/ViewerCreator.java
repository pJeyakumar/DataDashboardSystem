package httpTest;

import javax.naming.spi.DirStateFactory.Result;

public class ViewerCreator 
{
	public static Viewer createViewer(ViewerType viewer) 
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
