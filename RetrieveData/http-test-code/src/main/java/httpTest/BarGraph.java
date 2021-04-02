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
	protected void display(ArrayList<Double>[] data, ArrayList<Integer>[]  years, String[] types, JPanel plotArea, String[] units, String analysisID) 
	{
		
	}
	
}
