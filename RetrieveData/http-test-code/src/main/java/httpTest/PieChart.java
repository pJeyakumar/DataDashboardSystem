package httpTest;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

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
		chartPanel.setChart(pieChart);
	}
	
	public void setChartPanel(ChartPanel panel) 
	{
		this.chartPanel = panel;
	}
	
	
}
