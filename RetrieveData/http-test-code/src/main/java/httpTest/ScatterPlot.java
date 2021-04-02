package httpTest;

import java.util.ArrayList;

import javax.swing.JPanel;

public class ScatterPlot extends Viewer
{
	ScatterPlot()
	{
		super(ViewerType.SCATTERPLOT);
	}
	
	@Override
	protected void display(ArrayList<Double>[] data, int[] years, String[] types, JPanel plotArea) 
	{
		// create TimeSeries for each series in data
		
		// group TimeSeries in TimeSeriesCollections by units
		
		// create XYPlot (XYPlot plot = new XYPlot();)
		
		// create XYItemRenderer for EACH TimeSeriesCollection
		
		// set the data sets and renderers for the plot
		
		// set Domain axis (only 1 for Years)
		
		// set Range axis (at most 2) [provide names]
		
		// mapDataSet to range Axis for each TimeSeriesCollection
		
		// create JFreeChart with Title, Font and plot
		
		// create ChartPanel using JFreeChart and set dimensions, border and background
		
		// add ChartPanel to JPanel plotArae
	}
	
}
