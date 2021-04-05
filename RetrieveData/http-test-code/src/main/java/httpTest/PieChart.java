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
	private ChartPanel chartPanel;
	PieChart()
	{
		super(ViewerType.PIECHART);
	}
	
	@Override
	protected void display(JPanel plotArea, ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID) 
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(data[0].get(0), dataNames[0], "");
		dataset.addValue(100 - data[0].get(0), dataNames[1], "");
		
		JFreeChart pieChart = ChartFactory.createMultiplePieChart(analysisID, dataset,
				TableOrder.BY_COLUMN, true, true, false);
		
		chartPanel.setChart(pieChart);
	}
	
	public void setChartPanel(ChartPanel panel) 
	{
		this.chartPanel = panel;
	}
	
	
}
