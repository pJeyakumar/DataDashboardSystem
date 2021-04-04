package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: Class responsible for rendering the line graph viewer and adding that render to the ChartPanel
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

public class LineGraph extends Viewer
{
	private ChartPanel chartPanel;
	LineGraph()
	{
		super(ViewerType.LINEGRAPH);
	}

	@Override
	protected void display(JPanel plotArea, ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID) 
	{
		// Variable Declaration
		int numTS = data.length;
		int numTSC = axisNames.length;
		
		// create TimeSeries for each series in data
		TimeSeries[] seriesArray = new TimeSeries[numTS];
		for(int i = 0; i < numTS; i++)
		{
			// Find out number of years for that series
			int numYears = years[i].size();
			// Create a new TimeSeries using the series name
			seriesArray[i] = new TimeSeries(dataNames[i]);
			// For every year in the specified series, add the Year and its corresponding data value to that series
			for(int j = 0; j < numYears; j++)
			{
				if(data[i].get(j) != -1) 
				{
					seriesArray[i].add(new Year(years[i].get(j)), data[i].get(j));
				}
			}
		}
		
		// group TimeSeries in TimeSeriesCollections by units
		TimeSeriesCollection[] dataSet = new TimeSeriesCollection[numTSC];
		for (int i = 0 ; i < numTSC ; i++) 
		{
			dataSet[i] = new TimeSeriesCollection();
		}
		
		// If there are two TSCollections, add first series to one collection, and all the rest to the other
		if (numTSC == 2) 
		{
			dataSet[0].addSeries(seriesArray[0]);
			for (int i = 1; i < numTS; i++) 
			{
				dataSet[1].addSeries(seriesArray[i]);
			}
		}
		// Otherwise ad all time series into a single collection
		else
		{
			for (int i = 0; i < numTS; i++) 
			{
				dataSet[0].addSeries(seriesArray[i]);
			}
		}
		
		// create XYPlot 
		XYPlot plot = new XYPlot();
		// create XYItemRenderer array
		XYSplineRenderer[] itemRenArray = new XYSplineRenderer[numTSC];
		
		// create XYItemRenderer for EACH TimeSeriesCollection
		// set the data sets and renderers for the plot\		
		for(int k = 0; k < numTSC; k++) 
		{
			// create XYLineAndShapeRenderer for that TimeSeriesCollection
			itemRenArray[k] = new XYSplineRenderer();
			// set the dataset for that TimeSeriesCollection
			plot.setDataset(k, dataSet[k]);
			// set the XYItemRenderer for that TimeSeriesCollection
			plot.setRenderer(k, itemRenArray[k]);
			// set Range axis (at most 2) [provide names]
			plot.setRangeAxis(k, new NumberAxis(axisNames[k]));
			// set the datasets to their corresponding y-axis
			plot.mapDatasetToRangeAxis(k, k);
		}
		
		// set Domain axis (only 1 for Years)
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		
		// create JFreeChart with Title, Font and plot
		JFreeChart lineGraph = new JFreeChart(analysisID,
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		
		chartPanel.setChart(lineGraph);
	
		// add ChartPanel to JPanel plotArea
		plotArea.add(chartPanel);
	}
	
	public void setChartPanel(ChartPanel panel) 
	{
		this.chartPanel = panel;
	}
}
