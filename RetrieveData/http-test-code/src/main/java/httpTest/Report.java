package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: Class responsible for rendering the report viewer and adding that render to the JScrollPane
 */
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Report extends Viewer
{
	// instance variable
	private JScrollPane output;
	// constructor 
	Report()
	{
		super(ViewerType.REPORT);
		this.name = "Report";
	}
	
	@Override
	protected void display(ArrayList<Double>[] data, 
			ArrayList<Integer>[] years, String[] dataNames, String[] axisNames, String analysisID) 
	{
		// create a JTextArea object
		JTextArea myReport = new JTextArea();
		// set the properties of the JTextArea
		myReport.setEditable(false);
		myReport.setPreferredSize(new Dimension(400, 300));
		myReport.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		myReport.setBackground(Color.white);
		// add title to the message
		String reportMessage = analysisID + "\n";
		// go through the number of years
		for(int i = 0; i < years[0].size(); i++) 
		{
			// add the year to the message
			reportMessage += "Year " + years[0].get(i) + ":\n";
			// add all the data values under that year to the message
			for(int j = 0; j < data.length; j++) 
			{
				// if the value IS a null value, we will write it down as a null value in the report
				if(data[j].get(i) == -1) 
				{
					reportMessage += "\t" + dataNames[j] + "=>" + "n/a" + "\n";
				}
				// otherwise add it as normal
				else 
				{
					reportMessage += "\t" + dataNames[j] + "=>" + data[j].get(i) + "\n";
				}
			}
		}
		// set the JTextArea text to the message we just created
		myReport.setText(reportMessage);
		// set the JScrollPane to display the JTextArea
		output.setViewportView(myReport);
		
	}
	public void setScrollPane (JScrollPane jScroll) 
	{
		this.output = jScroll;
	}
	
}