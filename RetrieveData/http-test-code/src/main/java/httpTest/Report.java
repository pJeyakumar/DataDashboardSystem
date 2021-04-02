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
	protected void display(ArrayList<Double>[] data, int[] years, String[] types, JPanel plotArea) 
	{
		
		
	}
	
}
