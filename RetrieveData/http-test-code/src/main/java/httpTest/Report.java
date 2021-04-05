package httpTest;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Report extends Viewer
{
	private JScrollPane targetPanel; 
	
	Report()
	{
		super(ViewerType.REPORT);
	}
	
	@Override
	protected void display(ArrayList<Double>[] data, ArrayList<Integer>[] years, String[] types, JPanel plotArea, String[] units, String analysisID) 
	{
		
		
	}
	
	public void setPanel(JScrollPane target) {
		this.targetPanel = target;
	}
	
}
