package httpTest;

import java.util.ArrayList;

import javax.swing.JPanel;

public class LineGraph extends Viewer
{
	LineGraph()
	{
		super(ViewerType.LINEGRAPH);
	}

	@Override
	protected void display(JPanel plotArea, ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID) 
	{
		
	}

}
