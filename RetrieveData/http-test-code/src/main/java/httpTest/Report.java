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

import java.util.regex.Matcher;
import java.util.regex.Pattern;




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
		int dataCount;
		// reserve number of lines, number of data values * number of series + number of years*2 (for \n) + 1 (for title)
		dataCount = data.length * data[0].size() + years[0].size()*2 + 1;
		System.out.println(dataCount);
		// create a JTextArea object
		JTextArea myReport = new JTextArea(dataCount,1);
		// set the properties of the JTextArea
		myReport.setEditable(false);
		myReport.setPreferredSize(new Dimension(400, 400));
		myReport.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		myReport.setBackground(Color.white);
		// add title to the message
		String reportMessage = analysisID + "\n";
		String dataString; 
		int len;
		// go through the number of years
		for(int i = 0; i < years[0].size(); i++) 
		{
			// add the year to the message
			reportMessage += "\nYear " + years[0].get(i) + "\n";
			// add all the data values under that year to the message
			for(int j = 0; j < data.length; j++) 
			{	
				dataString = dataNames[j].replaceAll("\\([^)]+\\)", "");
				len = dataString.length();
				// if the value IS a null value, we will write it down as a null value in the report
				if(data[j].get(i) == -1) 
				{
					reportMessage += dataString + " : " + "n/a" + "\n";
				}
				// otherwise add it as normal
				else 
				{
					reportMessage += dataString + " : " + String.format("%.2f", data[j].get(i))   + "\n";
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