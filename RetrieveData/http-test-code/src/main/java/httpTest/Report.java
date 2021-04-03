package httpTest;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Report extends Viewer
{
	Report()
	{
		super(ViewerType.REPORT);
	}
	
	@Override
	protected void display(JPanel plotArea, ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID) 
	{
		
	}
	
}
