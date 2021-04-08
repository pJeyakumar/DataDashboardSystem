package httpTest;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * NAME: John Palmer, Piranavan Jeyakumar
 * DATE: 2021-04-07
 * DESCRIPTION: Main display class. Provides the main interface for the Data Dashboard Software system 
 */

public class MainDisplay extends JFrame implements ActionListener{
	
	// Initialize global variables 
	private String countryChoice = null;	
	private int startYearChoice = -1;	
	private int endYearChoice = -1;	
	private String analysisID = null;	
	private Results myResults;  	
	private AnalysisDB analysisCheck;	
	private ArrayList<Viewer> myViewers;	
	private ArrayList<ChartPanel> myPanels;	
	private JScrollPane myReport;	
	private static final long serialVersionUID = 1L;	
	private final JPanel plotDisplay;
	private static MainDisplay instance;

	
	// Use a singleton design pattern
	public static MainDisplay getInstance() 
	{
		if (instance == null)
			instance = new MainDisplay();

		return instance;
	}
	
	// Buttons, drop downs and a hashmap
	JButton recalculate;
	JButton addView;
	JButton removeView;
	JComboBox<String> viewerBox;
	JComboBox<String> countryBox;
	JComboBox<String> startYearBox;
	JComboBox<String> endYearBox;
	JComboBox<String> analysisBox;
	HashMap<String, ViewerType> viewerMap; 
	
	/** DESCRIPTION: Constructor for the mainDisplay class 
	 */
	private MainDisplay() 
	{
		// Set window title
		super("Country Statistics");
		
		
		System.out.println("CONSTRUCTING MAIN DISPLAY...");
		
		// Set charts region
		JPanel east = new JPanel();
		plotDisplay = new JPanel();
		plotDisplay.setLayout(new GridLayout(2, 0));
		
		
		// Load the countries drop down menu 
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		countriesNames.add("USA");
		countriesNames.add("Canada");
		countriesNames.add("France");
		countriesNames.add("China");
		countriesNames.add("Brazil");
		countriesNames.add("Germany");
		countriesNames.add("Japan");
		countriesNames.add("Norway");
		countriesNames.add("Spain");
		countriesNames.add("India");
		countriesNames.add("Sri Lanka");
		countriesNames.sort(null);
		countryBox = new JComboBox<String>(countriesNames);

		
		// Load a vector containing all relevant years
		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		
		int s = 1985;
		int e = 2015;
		for (int i = e; i >= s; i--) {
			years.add("" + i);
		}
		
		// Initialize the start and end year drop down menus 
		startYearBox = new JComboBox<String>(years);
		startYearBox.setSelectedIndex(years.size()-6);
		endYearBox = new JComboBox<String>(years);
		endYearBox.setSelectedIndex(0);
		
		// Initialize user choices upon entering the program 
		countryChoice = "Brazil";
		startYearChoice = 1990;
		endYearChoice = e;
		analysisID = "Renewable electricity output vs Renewable energy consumption";
		
		// Create a new analysis DB object to check and verify the user choices
		analysisCheck = new AnalysisDB(analysisID);
		analysisCheck.validStartYr(startYearChoice);
		analysisCheck.validEndYr(endYearChoice);
		analysisCheck.validCountry(countryChoice);
		
		
		// Load the top menu 
		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countryBox);
		north.add(from);
		north.add(startYearBox);
		north.add(to);
		north.add(endYearBox);

		// Set bottom bar
		// analysis drop down default text
		JLabel methodLabel = new JLabel("		Choose analysis type");
		// Analysis drop down menu options
		Vector<String> methodNames = new Vector<String>();
		methodNames.add("Renewable electricity output vs Renewable energy consumption");
		methodNames.add("Ratio of Electricity production from coal sources vs Renewable electricity output");
		methodNames.add("Forest area (% of land area)");
		methodNames.add("Forest area (% of land area) vs Average GHG net emissions/removals by LUCF");
		methodNames.add("Agricultural Land vs NO2 Emissions vs Methane Emissions");
		methodNames.add("Total GHG Emissions vs % Urban Population vs % Fossil Fuel Energy Consumption");
		methodNames.add("Ratio of agricultural land (% of total area) vs forest land (%)");
		methodNames.add("Average agricultural land (% of land area)");
		
		// creating JComboBox object for analysis, with the analysis options
		analysisBox = new JComboBox<String>(methodNames);
		
		
		// Viewer menu dropdown menu and options 
		JLabel viewsLabel = new JLabel("Available Views: ");
		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		viewerBox = new JComboBox<String>(viewsNames);
		
		// Add buttons for recalculate, add and remove viewer
		recalculate = new JButton("Recalculate");
		addView = new JButton("+");
		removeView = new JButton("-");

		// Load the bottom display panel
		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewerBox);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		// add analysis drop down
		south.add(analysisBox);
		south.add(recalculate);
		
		// Load the viewerMap hash map with viewer names and types
		viewerMap = new HashMap<String, ViewerType>();
		viewerMap.put("Pie Chart", ViewerType.PIECHART); 
		viewerMap.put("Line Chart", ViewerType.LINEGRAPH); 
		viewerMap.put("Bar Chart", ViewerType.BARGRAPH); 
		viewerMap.put("Scatter Chart", ViewerType.SCATTERPLOT); 
		viewerMap.put("Report", ViewerType.REPORT); 
		
		
		// ----  SET UP DEFAULT DISPLAY -----
		
		// Main Plot 
		JPanel plotDisplay = new JPanel();
		plotDisplay.setLayout(new GridLayout(2, 0));
		
		
		
		// INITIALIZE REPORT SEPARATELY
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		JScrollPane outputScrollPane = new JScrollPane(report);
		
		myReport = outputScrollPane;
		plotDisplay.add(outputScrollPane);
		
		
	
		// INITIALIZE VIEWERS AND PANELS 
		myViewers = new ArrayList<Viewer>();
		myPanels = new ArrayList<ChartPanel>();
		
		for (int i = 0; i < 5; i++) {
			CategoryPlot plot = new CategoryPlot();
			JFreeChart chart = new JFreeChart("", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			
			myPanels.add(chartPanel);
			plotDisplay.add(chartPanel);
		}
		
		// Initialize a results object 
		myResults = new Results();
		
		
		// Add all action listeners 
		this.addActionListeners();
		

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(plotDisplay, BorderLayout.WEST);
	}
	
	/** DESCRIPTION: Function to handle set up the action listeners for all buttons and dropdown menus on the main display    
	 */
	public void addActionListeners() {
		
		// Buttons 
		recalculate.addActionListener(this); // recalculate
		addView.addActionListener(this);   // +
		removeView.addActionListener(this);  // -
		
		// Drop down menus 
		viewerBox.addActionListener(this);
		countryBox.addActionListener(this);
		startYearBox.addActionListener(this);
		endYearBox.addActionListener(this);
		analysisBox.addActionListener(this);
	}
	

	
	
	/** DESCRIPTION: Function to handle all cases of button presses and dropdown menu changes on the main display    
	 * @param press The ActionEvent object associated with a button press or JComboBox choice
	 */
	public void actionPerformed(ActionEvent press) 
	{

		// RECALCULATE BUTTON 
		if (press.getSource() == recalculate) 
		{

			// If proper choices have been loaded ...
			if(countryChoice != null && startYearChoice != -1 && endYearChoice != -1 && analysisID!=null && analysisCheck.allValid() && myViewers.size() > 0) 
			{
				
				// create a selection object
				Selection input = new Selection();
				
				// set the user choices in the selection object
				input.setCountry(countryChoice);
				input.setStartYear(startYearChoice);
				input.setEndYear(endYearChoice);
				input.setAnalysis(analysisID);
				
				// Create a new computation server to host the analysis
				ComputationServer cs = new ComputationServer();
				
				// set the selection object for comp server
				cs.setSelection(input);
				// create analysisstrat object with user chosen analysisID
				
				// set analysis object for comp server
				cs.setStrategy(analysisID);
				// run comp server strategy
				
				cs.runStrategy(myResults);
				
			}
			// User has not selected proper choices 
			else
			{
				// Default message
				String errorMessage = "You have INVALID / UNLOADED choices! Please fix the following: \n";
				
				// Get the boolean values from analysisDB object
				Boolean flags[] = analysisCheck.getTruth();
				
				// if country is invalid add it to the list
				if(!flags[0]) 
				{
					errorMessage += "Country\n";
				}
				// if sYear is invalid add it to the list
				if(!flags[1]) 
				{
					errorMessage += "Start Year\n";
				}	
				// if eYear is invalid add it to the list
				if(!flags[2]) 
				{
					errorMessage += "End Year\n";
				}
				// if Viewers is invalid add it to the list
				if(!flags[3] || myViewers.size() == 0) 
				{
					errorMessage += "Viewers\n";
				}
				JOptionPane.showMessageDialog(null, errorMessage, "Selection Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
        

		// ANALYSIS DROP DOWN MENU CHANGED 
		if(press.getSource() == analysisBox) 
		{
			// Get analysis option from the menu 
			String newAnalysis = (String) analysisBox.getSelectedItem();
			System.out.println(newAnalysis);
			
			// Proceed only if the analysis is different 
			if (this.analysisID != newAnalysis) {
				
				// If the existing analysis ID is not null, empty the viewers
				if (this.analysisID != null) {
					System.out.print("NEW ANALYSIS ...");
					System.out.println(newAnalysis);
					System.out.println("EMPTYING VIEWERS ...");
					
					// RESET ALL GRAPHS 
					CategoryPlot plot = new CategoryPlot();
					JFreeChart chart = new JFreeChart("", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					for (ChartPanel p : myPanels) p.setChart(chart);
					
					// RESET REPORT 
					JTextArea report = new JTextArea();
					report.setText("");
					myReport.setViewportView(report);
					
					// RESET THE VIEWERS LIST AND RESULTS 
					myViewers = new ArrayList<Viewer>();
					myResults.emptyViewers();
				}
				
				// Always set the new analysisID
				this.analysisID = newAnalysis;
				
				// Create a new analysisDB
				analysisCheck = new AnalysisDB(newAnalysis);
				
				// Check the current country and year entries to see whether theyre valid 
				// Set them to red if they are invalid, or white if they are valid
				if (!analysisCheck.validCountry(countryChoice))
				{
					countryBox.setBackground(Color.red);
				}
				else
				{
					countryBox.setBackground(Color.white);
				}
				if (!analysisCheck.validStartYr(startYearChoice)) 
				{
					startYearBox.setBackground(Color.red);
				}
				else 
				{
					startYearBox.setBackground(Color.white);
				}
				if (!analysisCheck.validEndYr(endYearChoice)) 
				{
					endYearBox.setBackground(Color.red);
				}
				else 
				{
					endYearBox.setBackground(Color.white);
				}
			}
			

		}
		
		// ADD VIEWER BUTTON PRESSED 
		if (press.getSource() == addView) {
			// Get chosen viewer 
			String selectedViewer = (String) viewerBox.getSelectedItem();
			System.out.print("ADDING VIEWER...");
			System.out.println(selectedViewer);
			
			// Proceed if the selected viewer is valid and it is NOT in the current viewer list 
			if (analysisCheck.validViewer(selectedViewer) && this.findViewer(viewerMap.get(selectedViewer)) == -1) {
				
				viewerBox.setBackground(Color.white);
				
				// Create a new viewer creator factory object 
				ViewerCreator myCreator = new ViewerCreator();
				
				// Create a report pane 
				if (selectedViewer.equals("Report")) {
					
					// Fill in the panel with a temporary report 
					JTextArea report = new JTextArea();
					report.setFont(new Font("Serif", java.awt.Font.BOLD, 18));
					report.setText("\tTEXTUAL REPORT");
					myReport.setViewportView(report);
					
					// Call the viewer creator, create a new bar chart, and attach it 
					Viewer newViewer = myCreator.createViewer(viewerMap.get(selectedViewer));
					
					
					Report myRep = (Report) newViewer;
					
					// Set the panel for the report object
					myRep.setScrollPane(myReport);
					
					// Add the report to the viewers 
					myViewers.add(myRep);
					
					// Attach the report to the results 
					myResults.attachViewer(myRep);
					
				// Create one of the four chart objects 
				}else {
					ChartPanel chartPanel;
					JFreeChart chart;
					Viewer newViewer;
					
					// Bar chart 
					if (selectedViewer.equals("Bar Chart")){
						
						CategoryPlot plot = new CategoryPlot();
						chart = new JFreeChart("BAR CHART",new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
						
						// Display a temporary empty plot window
						myPanels.get(1).setChart(chart);
						
						// Call the viewer creator, create a new bar chart, and attach it 
						newViewer = myCreator.createViewer(viewerMap.get(selectedViewer));
						
						// Set the panel for the viewer
						newViewer.setPanel(myPanels.get(1));
					
					// Scatter plot 
					}else if(selectedViewer.equals("Scatter Chart")) {
						XYPlot plot = new XYPlot();
						chart = new JFreeChart("SCATTER PLOT", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

						// Display a temporary empty plot window
						myPanels.get(2).setChart(chart);
						
						// Call the viewer creator, create a new bar chart, and attach it 
						newViewer = myCreator.createViewer(viewerMap.get(selectedViewer));
						
						// Set the panel for the viewer
						newViewer.setPanel(myPanels.get(2));
						
					// Line chart 	
					}else if(selectedViewer.equals("Line Chart")) {
						XYPlot plot = new XYPlot();
						chart = new JFreeChart("LINE CHART",new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
						
						// Display a temporary empty plot window
						myPanels.get(3).setChart(chart);
						
						// Call the viewer creator, create a new bar chart, and attach it 
						newViewer = myCreator.createViewer(viewerMap.get(selectedViewer));
						
						// Set the panel for the viewer
						newViewer.setPanel(myPanels.get(3));
					
					// Pie chart
					}else{
						DefaultCategoryDataset dataset = new DefaultCategoryDataset();
						chart = ChartFactory.createMultiplePieChart("PIE CHART", dataset,TableOrder.BY_COLUMN, true, true, false);

						// Display a temporary empty plot window
						myPanels.get(4).setChart(chart);
						
						// Call the viewer creator, create a new bar chart, and attach it 
						newViewer = myCreator.createViewer(viewerMap.get(selectedViewer));
						
						// Set the panel for the viewer
						newViewer.setPanel(myPanels.get(4));
					}
					
					// Attaching viewer to the results object 
					myResults.attachViewer(newViewer);
					
					// Add viewer to the viewers list 
					myViewers.add(newViewer);
					
					System.out.printf("CURRENT # OF VIEWERS: %d", myViewers.size());
					this.pack();
				}
				
			// If the chosen viewer is already in the list, display a message 
			}else if (analysisCheck.validViewer(selectedViewer) && this.findViewer(viewerMap.get(selectedViewer)) != -1){
				System.out.print("Sorry! Only one of each viewer type is allowed.");

			// Otherwise, must be an invalid viewer 
			}else {
				System.out.print("Invalid Viewer!");
				viewerBox.setBackground(Color.red);
			}
		}
			
		// REMOVE BUTTON 
		if (press.getSource() == removeView) {
			
			// Get user selection
			String selectedViewer = (String) viewerBox.getSelectedItem();
				
			// Only proceed if the selected viewer is found in the viewer list 
			if(this.findViewer(viewerMap.get(selectedViewer)) != -1) {
				
				// Remove the report object 
				if (selectedViewer.equals("Report")) {
					JTextArea report = new JTextArea();
					myReport.setViewportView(report);
					
					// Remove the viewer from the list 
					removeViewer(ViewerType.REPORT);
				
				// Remove one of the four chart objects 
				}else {
					CategoryPlot plot = new CategoryPlot();
					JFreeChart chart = new JFreeChart("", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					
					if (selectedViewer.equals("Bar Chart")){	
						// Set a blank chart
						myPanels.get(1).setChart(chart);
						
						// Call the remove viewer method 
						removeViewer(ViewerType.BARGRAPH);
					}else if(selectedViewer.equals("Scatter Chart")) {
						// Set a blank chart
						myPanels.get(2).setChart(chart);
						
						// Call the remove viewer method 
						removeViewer(ViewerType.SCATTERPLOT);
					}else if(selectedViewer.equals("Line Chart")) {
						// Set a blank chart
						myPanels.get(3).setChart(chart);
						
						// Call the remove viewer method 
						removeViewer(ViewerType.LINEGRAPH);
					}else{
						// Set a blank chart
						myPanels.get(4).setChart(chart);
						
						// Call the remove viewer method 
						removeViewer(ViewerType.PIECHART);
					}
				}
			
			// Viewer is not a valid choice
			}else {
				System.out.println("INVALID VIEWER CHOICE");
			}
			
			
		}
		
		// COUNTRY DROP DOWN MENU CHANGED 
		if (press.getSource() == countryBox) {
			
			String selectedCountry = (String) countryBox.getSelectedItem();
			
			// Perform check that the country is valid given the analysis 
			if (analysisCheck.validCountry(selectedCountry)) {
				
				countryBox.setBackground(Color.white);
				// Add country to the saved user choice
				countryChoice = selectedCountry;
			
			// Country is not valid 
			}else {
				
				System.out.println("INVALID COUNTRY CHOICE");
				countryBox.setBackground(Color.red);
			}
		}
		
		// START YEAR CHOICE CHANGED 
		if (press.getSource() == startYearBox) {
			
			// Get users input for start year 
			int selectedYr = Integer.valueOf((String)startYearBox.getSelectedItem());
			
			// Perform check that the start year is valid given the analysis 
			if (analysisCheck.validStartYr(selectedYr)) {
				startYearBox.setBackground(Color.white);
				
				// Add start year to saved choice
				startYearChoice = selectedYr;
			
			// Perform 
			}else {
				// NOT VALID 
				System.out.println("INVALID START YEAR CHOICE");
				startYearBox.setBackground(Color.red);
			}
		}
		
		// END YEAR CHOICE CHANGED 
		if (press.getSource() == endYearBox) {
			
			// Perform check that the end year is valid given the analysis 
			int selectedYr = Integer.valueOf((String)endYearBox.getSelectedItem());
			
			// Valid end year is chosen
			if (analysisCheck.validEndYr(selectedYr)) {
				endYearBox.setBackground(Color.white);
				
				// Add end year to the saved user choice
				endYearChoice = selectedYr;
				
			// User end year choice is not valid 
			}else {
				System.out.println("INVALID END YEAR CHOICE");
				endYearBox.setBackground(Color.red);
			}
		}
		
	}
	
	/** DESCRIPTION: Private method to remove a viewer from the viewer list        
	 * @param type The ViewerType of the viewer to be removed 
	 */
	private void removeViewer(ViewerType type) {
		int pos = this.findViewer(type); 
		
		// Try to detach and remove the viewer 
		try {
			if (pos != -1) {
				myResults.detachViewer(myViewers.get(pos).getType());
				myViewers.remove(pos);
			}
		// Catch exception 
		}catch(ViewerNotFoundException e ) {
			e.printStackTrace();
		}
	}
		
	/** DESCRIPTION: Private method to locate the position of a viewer within the viewer list     
	 * @param type The ViewerType of the viewer to be found 
	 * @return An integer of the viewer position or -1 if not found 
	 */
	public int findViewer(ViewerType type) {
		
		// Search for viewer in the viewer list 
		for (int i = 0 ; i < myViewers.size(); i++) {
			if (myViewers.get(i).getType() == type) {
				return i;
			}
		}
		return -1;

	}

	public static void main(String[] args) {

		JFrame frame = MainDisplay.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}
}