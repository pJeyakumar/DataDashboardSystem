package httpTest;

import java.util.ArrayList;

import javax.swing.JPanel;

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
		
	}
	
}
