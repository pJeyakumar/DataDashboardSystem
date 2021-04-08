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

public class MainDisplay extends JFrame implements ActionListener{
	/**
	 * 
	 */
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

	public static MainDisplay getInstance() 
	{
		if (instance == null)
			instance = new MainDisplay();

		return instance;
	}
	
	JButton recalculate;
	JButton addView;
	JButton removeView;
	JComboBox<String> viewerBox;
	JComboBox<String> countryBox;
	JComboBox<String> startYearBox;
	JComboBox<String> endYearBox;
	JComboBox<String> analysisBox;
	HashMap<String, ViewerType> viewerMap; 
	
	
	public String AnalysisID;

	private MainDisplay() 
	{
		// Set window title
		super("Country Statistics");
		
		
		System.out.println("CONSTRUCTING MAIN DISPLAY...");
		
		// Set charts region
		JPanel east = new JPanel();
		plotDisplay = new JPanel();
		plotDisplay.setLayout(new GridLayout(2, 0));
		
		
		// COUNTRIES 
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

		
		// YEARS 
		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		
		int s = 1985;
		int e = 2015;
		for (int i = e; i >= s; i--) {
			years.add("" + i);
		}
		
		
		startYearBox = new JComboBox<String>(years);
		startYearBox.setSelectedIndex(years.size()-6);
		endYearBox = new JComboBox<String>(years);
		endYearBox.setSelectedIndex(0);
		
		countryChoice = "Brazil";
		
		startYearChoice = 1990;
		endYearChoice = e;
		
		analysisID = "Renewable electricity output vs Renewable energy consumption";
		analysisCheck = new AnalysisDB(analysisID);
		
		analysisCheck.validStartYr(startYearChoice);
		analysisCheck.validEndYr(endYearChoice);
		analysisCheck.validCountry(countryChoice);
		
		
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
		
		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		viewerBox = new JComboBox<String>(viewsNames);
		
		recalculate = new JButton("Recalculate");
		addView = new JButton("+");
		removeView = new JButton("-");

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewerBox);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		// add analysis drop down
		south.add(analysisBox);
		south.add(recalculate);
		
		viewerMap = new HashMap<String, ViewerType>();
		viewerMap.put("Pie Chart", ViewerType.PIECHART); 
		viewerMap.put("Line Chart", ViewerType.LINEGRAPH); 
		viewerMap.put("Bar Chart", ViewerType.BARGRAPH); 
		viewerMap.put("Scatter Chart", ViewerType.SCATTERPLOT); 
		viewerMap.put("Report", ViewerType.REPORT); 
		
		// ----  SET UP EMPTY PANELS  -----
		
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
		
		myResults = new Results();
		
		this.addActionListeners();
		

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(plotDisplay, BorderLayout.WEST);
	}
	public void addActionListeners() {
		
		recalculate.addActionListener(this); // recalculate
		addView.addActionListener(this);   // +
		removeView.addActionListener(this);  // -
		
		
		viewerBox.addActionListener(this);
		countryBox.addActionListener(this);
		startYearBox.addActionListener(this);
		endYearBox.addActionListener(this);
		analysisBox.addActionListener(this);
	}
	

	
	

	public void actionPerformed(ActionEvent press) 
	{

		// if the recalculate button is pressed
		if (press.getSource() == recalculate) 
		{
			System.out.println("RECALCULATE STARTING...");
			// check if all the choices are not null and are all valid
			if(countryChoice != null && startYearChoice != -1 && endYearChoice != -1 && analysisID!=null && analysisCheck.allValid()) 
			{
				// create a selection object
				System.out.println("LOADING SELECTION...");
				Selection input = new Selection();
				// set the user choices in the selection object
				input.setCountry(countryChoice);
				input.setStartYear(startYearChoice);
				input.setEndYear(endYearChoice);
				input.setAnalysis(analysisID);
				
				// create a comp server object
				System.out.printf("%s  %s  %d  %d  \n", input.getAnalysis(), input.getCountry(), input.getStartYr(), input.getEndYr());
				
				ComputationServer cs = new ComputationServer();
				// set the selection object for comp server
				cs.setSelection(input);
				// create analysisstrat object with user chosen analysisID
				AnalysisStrategy analysis = AnalysisCreator.create(analysisID);
				// set analysis object for comp server
				cs.setStrategy(analysis);
				// run comp server strategy
				
				cs.runStrategy(myResults);
				
			}
			else
			{
				// Default message
				String errorMessage = "You have INVALID / UNLOADED choices! Please fix the following: \n";
				// get the boolean values
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
				if(!flags[3]) 
				{
					errorMessage += "Viewers\n";
				}
				JOptionPane.showMessageDialog(null, errorMessage, "Selection Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
        

		if(press.getSource() == analysisBox) 
		{
			// get analysis drop down menu box
			System.out.println("CHANGING ANALYSIS...");
			String newAnalysis = (String) analysisBox.getSelectedItem();
			System.out.println(newAnalysis);
			
			
			// Proceed if there is a change 
			if (this.analysisID != newAnalysis) {
				// If the existing analysis ID is not null, empty the viewers
				if (this.analysisID != null) {
					System.out.println("NEW ANALYSIS, EMPTYING VIEWERS...");
					
					
					// RESET ALL GRAPHS 
					CategoryPlot plot = new CategoryPlot();
					JFreeChart chart = new JFreeChart("", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					for (ChartPanel p : myPanels) p.setChart(chart);
					
					// RESET REPORT 
					JTextArea report = new JTextArea();
					report.setText("");
					
					myReport.setViewportView(report);
					
					myViewers = new ArrayList<Viewer>();
					myResults.emptyViewers();
				}
				
				// Always set the new analysisID and create a new analysisDB
				this.analysisID = newAnalysis;
				analysisCheck = new AnalysisDB(newAnalysis);
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
		
		if (press.getSource() == addView) {
			String selectedViewer;
			selectedViewer = (String) viewerBox.getSelectedItem();
			
			System.out.print("ADDING VIEWER...");
			System.out.println(selectedViewer);
			
			ViewerCreator myCreator = new ViewerCreator();
			
			
			if (analysisCheck.validViewer(selectedViewer) && this.findViewer(viewerMap.get(selectedViewer)) == -1) {
				System.out.print("VALID VIEWER!");
				viewerBox.setBackground(Color.white);

				
				if (selectedViewer.equals("Report")) {
					
					JTextArea report = new JTextArea();
					report.setFont(new Font("Serif", java.awt.Font.BOLD, 18));
					report.setText("\tTEXTUAL REPORT");
					myReport.setViewportView(report);
					
					// Call the viewer creator, create a new bar chart, and attach it 
					Viewer newViewer = myCreator.createViewer(viewerMap.get(selectedViewer));
					
					
					Report myRep = (Report) newViewer;
					myRep.setScrollPane(myReport);
					
					myViewers.add(myRep);
					myResults.attachViewer(myRep);
					
				}else {
					ChartPanel chartPanel;
					JFreeChart chart;
					Viewer newViewer;
					
					if (selectedViewer.equals("Bar Chart")){
						
						CategoryPlot plot = new CategoryPlot();
						chart = new JFreeChart("BAR CHART",new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
						
						// Display a temporary empty plot window
						myPanels.get(1).setChart(chart);
						
						// Call the viewer creator, create a new bar chart, and attach it 
						newViewer = myCreator.createViewer(viewerMap.get(selectedViewer));
						newViewer.setPanel(myPanels.get(1));
						
					}else if(selectedViewer.equals("Scatter Chart")) {
						XYPlot plot = new XYPlot();
						chart = new JFreeChart("SCATTER PLOT", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

						// Display a temporary empty plot window
						myPanels.get(2).setChart(chart);
						
						// Call the viewer creator, create a new bar chart, and attach it 
						newViewer = myCreator.createViewer(viewerMap.get(selectedViewer));
						newViewer.setPanel(myPanels.get(2));
						
						
					}else if(selectedViewer.equals("Line Chart")) {
						XYPlot plot = new XYPlot();
						chart = new JFreeChart("LINE CHART",new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
						
						// Display a temporary empty plot window
						myPanels.get(3).setChart(chart);
						
						// Call the viewer creator, create a new bar chart, and attach it 
						newViewer = myCreator.createViewer(viewerMap.get(selectedViewer));
						
						newViewer.setPanel(myPanels.get(3));
						
					}else{
						DefaultCategoryDataset dataset = new DefaultCategoryDataset();
						chart = ChartFactory.createMultiplePieChart("PIE CHART", dataset,TableOrder.BY_COLUMN, true, true, false);

						// Display a temporary empty plot window
						myPanels.get(4).setChart(chart);
						
						// Call the viewer creator, create a new bar chart, and attach it 
						newViewer = myCreator.createViewer(viewerMap.get(selectedViewer));
						
						newViewer.setPanel(myPanels.get(4));
					}
					System.out.println("ATTACHING VIEWER");
					myResults.attachViewer(newViewer);
					System.out.println("ADDING VIEWER");
					myViewers.add(newViewer);
					System.out.printf("CURRENT # OF VIEWERS: %d", myViewers.size());
					this.pack();
				}
			}else if (analysisCheck.validViewer(selectedViewer) && this.findViewer(viewerMap.get(selectedViewer)) != -1){
				System.out.print("SORRY! ONLY ONE OF EACH VIEWER ALLOWED.");

			}else {
				System.out.print("INVALID VIEWER!");
				viewerBox.setBackground(Color.red);
			}
		}
			
		// REMOVE BUTTON 
		if (press.getSource() == removeView) {
			String selectedViewer;
			selectedViewer = (String) viewerBox.getSelectedItem();
						
			if(this.findViewer(viewerMap.get(selectedViewer)) != -1) {
				
				if (selectedViewer.equals("Report")) {
					JTextArea report = new JTextArea();
					myReport.setViewportView(report);
					
					// Call the viewer creator, create a new bar chart, and attach it 
					removeViewer(ViewerType.REPORT);
					
				}else {
					CategoryPlot plot = new CategoryPlot();
					JFreeChart chart = new JFreeChart("", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					
					if (selectedViewer.equals("Bar Chart")){					
						myPanels.get(1).setChart(chart);
						
						removeViewer(ViewerType.BARGRAPH);
					}else if(selectedViewer.equals("Scatter Chart")) {
						myPanels.get(2).setChart(chart);
						
						removeViewer(ViewerType.SCATTERPLOT);
					}else if(selectedViewer.equals("Line Chart")) {
						myPanels.get(3).setChart(chart);
						
						removeViewer(ViewerType.LINEGRAPH);
					}else{
						myPanels.get(4).setChart(chart);
						
						removeViewer(ViewerType.PIECHART);
					}
				}
	
			}else {
				System.out.println("INVALID OPTION -- VIEWER NOT FOUND");
			}
			
			
		}
		if (press.getSource() == countryBox) {
			// Perform check that the country is valid given the analysis 
			String selectedCountry = (String) countryBox.getSelectedItem();
			
			// Add the country to the 
			System.out.println(analysisCheck == null);
			if (analysisCheck.validCountry(selectedCountry)) {
				// VALID COUNTRY 
				countryBox.setBackground(Color.white);
				// Add country to the saved user choice
				countryChoice = selectedCountry;
				
			}else {
				// NOT VALID 
				System.out.println("INVALID COUNTRY CHOICE");
				countryBox.setBackground(Color.red);
			}
		}
		
		if (press.getSource() == startYearBox) {
			// Perform check that the country is valid given the analysis 
			int selectedYr = Integer.valueOf((String)startYearBox.getSelectedItem());
			
			if (analysisCheck.validStartYr(selectedYr)) {
				// VALID COUNTRY 
				startYearBox.setBackground(Color.white);
				// Add country to the saved user choice
				startYearChoice = selectedYr;
				
			}else {
				// NOT VALID 
				System.out.println("INVALID COUNTRY CHOICE");
				startYearBox.setBackground(Color.red);
			}
		}
		
		if (press.getSource() == endYearBox) {
			// Perform check that the country is valid given the analysis 
			int selectedYr = Integer.valueOf((String)endYearBox.getSelectedItem());
			
			if (analysisCheck.validEndYr(selectedYr)) {
				// VALID COUNTRY 
				endYearBox.setBackground(Color.white);
				// Add country to the saved user choice
				endYearChoice = selectedYr;
				
			}else {
				// NOT VALID 
				System.out.println("INVALID COUNTRY CHOICE");
				endYearBox.setBackground(Color.red);
			}
		}
		
	}
	

	public void removeViewer(ViewerType type) {
		int pos = this.findViewer(type); 
		try {
			if (pos != -1) {
				myResults.detachViewer(myViewers.get(pos).getType());
				myViewers.remove(pos);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
		
	
	public int findViewer(ViewerType type) {
		
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
	// TODO Auto-generated method stub

}