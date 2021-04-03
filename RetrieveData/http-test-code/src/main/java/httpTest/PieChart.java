package httpTest;

import java.util.ArrayList;

import javax.swing.JPanel;

public class PieChart extends Viewer
{
	PieChart()
	{
		super(ViewerType.PIECHART);
	}
	
	@Override
	protected void display(JPanel plotArea, ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID) 
	{
		
	}
	
}
