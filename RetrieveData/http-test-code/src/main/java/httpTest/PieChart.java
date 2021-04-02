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
	protected void display(ArrayList<Double>[] data, ArrayList<Integer>[] years, String[] types, JPanel plotArea, String[] units, String analysisID) 
	{
		
	}
	
}
