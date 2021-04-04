package httpTest;

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
	BarGraph()
	{
		super(ViewerType.BARGRAPH);
	}
	
	@Override
	protected void display(JPanel plotArea, ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID) 
	{
		// Variable Declaration
		int numData = data.length;
		int numYears = years[0].size();
		int numDCD = axisNames.length;
		
		// create DefaultCategoryDataset array
		DefaultCategoryDataset[] dataSet = new DefaultCategoryDataset[numDCD];
		// Based on 
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
		
		// Otherwise ad all the data into a single Dataset
		else
		{
			for (int l = 0; l < numYears; l++) 
			{
				for(int m = 0; m < numData; m++	) 
				{
					dataSet[0].setValue(data[m].get(l), dataNames[m], years[m].get(l));
				}
			}
		}
		
		CategoryPlot plot = new CategoryPlot();
		BarRenderer[] barRend = new BarRenderer[numDCD];
		
		for(int n = 0; n < numDCD; n++) 
		{
			barRend[n] = new BarRenderer();
			plot.setDataset(n,dataSet[n]);
			plot.setRenderer(n, barRend[n]);
			plot.setRangeAxis(n, new NumberAxis(axisNames[n]));
			plot.mapDatasetToRangeAxis(n, n);
			
		}
		
		// set Domain axis (only 1 for Years)
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		
		JFreeChart barChart = new JFreeChart(analysisID,
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		// add ChartPanel to JPanel plotArea
		plotArea.add(chartPanel);
	}
	
}
