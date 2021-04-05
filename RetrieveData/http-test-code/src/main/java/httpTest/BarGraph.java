package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: Class responsible for rendering the bar graph viewer and adding that render to the ChartPanel
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;

public class BarGraph extends Viewer
{
	// constructor 
	BarGraph()
	{
		super(ViewerType.BARGRAPH);
		this.name = "Bar Chart";
	}
	
	@Override
	protected void display(ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID) 
	{
		// Variable Declaration
		int numData = data.length;
		int numYears = years[0].size();
		int numDCD = axisNames.length;
		
		// create DefaultCategoryDataset array
		DefaultCategoryDataset[] dataSet = new DefaultCategoryDataset[numDCD];
		// for each unique axis we have, add a DCD element to our DCD array
		for (int i = 0 ; i < numDCD ; i++) 
		{
			dataSet[i] = new DefaultCategoryDataset();
		}
		
		// If there are two types of Datasets, add first set of Data to one Dataset, and all the rest to the other
		if (numDCD == 2) 
		{
			for(int j = 0; j < numYears; j++) 
			{
				dataSet[0].setValue(data[0].get(j), dataNames[0], years[0].get(j));				
			}
			for (int k = 0; k < numYears; k++) 
			{
				dataSet[1].setValue(data[1].get(k), dataNames[1], years[1].get(k));
				dataSet[1].setValue(data[2].get(k), dataNames[2], years[2].get(k));
			}
		}
		// print out an error message if there are more than 2 DCDs
		else if(numDCD > 2) 
		{
			System.out.println("ERROR: there should not be more than 2 DefaultCategoryDatasets - bar");
		}
		// Otherwise ad all the data into a single Dataset
		else
		{
			for (int l = 0; l < numYears; l++) 
			{
				for(int m = 0; m < numData; m++) 
				{
					dataSet[0].setValue(data[m].get(l), dataNames[m], years[m].get(l));
				}
			}
		}
		
		// create a CategoryPlot object
		CategoryPlot plot = new CategoryPlot();
		// create a BarRenderer array
		BarRenderer[] barRend = new BarRenderer[numDCD];
		
		// for the number of DCDs we have:
		for(int n = 0; n < numDCD; n++) 
		{
			// add a BarRenderer object to our BarRenderer array
			barRend[n] = new BarRenderer();
			// set the dataset to our plot
			plot.setDataset(n,dataSet[n]);
			// set the bar renderer to our plot
			plot.setRenderer(n, barRend[n]);
			// set the y axis titles
			plot.setRangeAxis(n, new NumberAxis(axisNames[n]));
			// map our data to their corresponding y axis
			plot.mapDatasetToRangeAxis(n, n);
			
		}
		
		// set Domain axis (only 1 for Years)
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		
		// create JFreeChart using analysisID as title, and the plot we just created 
		JFreeChart barChart = new JFreeChart(analysisID,
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		
		// set the chart Panel with the JFreeChart
		targetPanel.setChart(barChart);	
	}
	
	/*
	public void setChartPanel(ChartPanel panel) 
	{
		this.chartPanel = panel;
	}
	(*/
}
