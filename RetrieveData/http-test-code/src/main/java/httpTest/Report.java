package httpTest;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
		JTextArea myReport = new JTextArea();
		myReport.setEditable(false);
		myReport.setPreferredSize(new Dimension(400, 300));
		myReport.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		myReport.setBackground(Color.white);
		String reportMessage = analysisID + "\n";
		for(int i = 0; i < years[0].size(); i++) 
		{
			reportMessage += "Year " + years[0].get(i) + ":\n";
			for(int j = 0; j < data.length; j++) 
			{
				reportMessage += "\t" + dataNames[j] + "=>" + data[j].get(i) + "\n";
			}
		}
		myReport.setText(reportMessage);
		JScrollPane output = new JScrollPane(myReport);
		
		plotArea.add(output);
		
	}
	
}
