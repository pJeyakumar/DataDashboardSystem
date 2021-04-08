package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: Class responsible for rendering the pie chart viewer 
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
	/* DESCRIPTION: Constructor class that will set ViewerType by calling parent class (abstract Viewer class) and set name instance variable
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */
	PieChart()
	{
		super(ViewerType.PIECHART);
		this.name = "Pie Chart";
	}
	
	/* DESCRIPTION: Method will, using the parameters given by the AnalysisStrategy class, will render a Pie Chart using the data values given and the 
	 * 				data names. There should only be 2 values, the value given by the analysis, and the other value that is calculated here
	 * INPUT: ArrayList<Double>[], ArrayList<Integer>[], String[], String[]. String
	 * OUTPUT: N/A
	 */
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
