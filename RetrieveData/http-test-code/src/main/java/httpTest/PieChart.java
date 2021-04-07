package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: Class responsible for rendering the pie chart viewer and adding that render to the ChartPanel
 */
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

public class PieChart extends Viewer
{
	// constructor
	PieChart()
	{
		super(ViewerType.PIECHART);
		this.name = "Pie Chart";
	}
	
	@Override
	protected void display(ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID) 
	{
		// create DefaultCategoryDataset object
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// add the calculated value
		dataset.addValue(data[0].get(0), dataNames[0], "");
		// add the OTHER value, 100 - calculated value
		dataset.addValue(100 - data[0].get(0), dataNames[1], "");
		
		// create JFreeChart, with given analysis name, dataset that was just created and other properties
		JFreeChart pieChart = ChartFactory.createMultiplePieChart(analysisID, dataset,
				TableOrder.BY_COLUMN, true, true, false);
		
		// set the chart panel
		targetPanel.setChart(pieChart);
	}
	
}
