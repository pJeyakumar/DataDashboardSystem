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
	
	private AnalysisDB analysisCheck = null;
	
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
	JComboBox<String> chosenViewer;
	JComboBox<String> country;
	JComboBox<String> startYear;
	JComboBox<String> endYear;
	JComboBox<String> analysis;
	
	
	public String AnalysisID;

	private MainDisplay() 
	{
		// Set window title
		super("Country Statistics");
		
		// Set charts region
		JPanel east = new JPanel();
		plotDisplay = new JPanel();
		plotDisplay.setLayout(new GridLayout(2, 0));
		
		
		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		countriesNames.add("USA");
		countriesNames.add("Canada");
		countriesNames.add("France");
		countriesNames.add("China");
		countriesNames.add("Brazil");
		countriesNames.sort(null);
		country = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		for (int i = 2021; i >= 2010; i--) {
			years.add("" + i);
		}
		startYear = new JComboBox<String>(years);
		endYear = new JComboBox<String>(years);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(country);
		north.add(from);
		north.add(startYear);
		north.add(to);
		north.add(endYear);

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
		methodNames.add("Average Agricultural land (% of land area)");
		// creating JComboBox object for analysis, with the analysis options
		analysis = new JComboBox<String>(methodNames);
		
		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		chosenViewer = new JComboBox<String>(viewsNames);
		
		recalculate = new JButton("Recalculate");
		addView = new JButton("+");
		removeView = new JButton("-");

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(chosenViewer);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		// add analysis drop down
		south.add(analysis);
		south.add(recalculate);

		JPanel plotDisplay = new JPanel();
		plotDisplay.setLayout(new GridLayout(2, 0));
		
		myViewers = new ArrayList<Viewer>();
		myPanels = new ArrayList<ChartPanel>();
		
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		JScrollPane outputScrollPane = new JScrollPane(report);
		
		myReport = outputScrollPane;
		plotDisplay.add(outputScrollPane);
		
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
		
		this.addActionListeners();
		

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(plotDisplay, BorderLayout.WEST);
	}
	public void addActionListeners() {
		recalculate.addActionListener(this);
		addView.addActionListener(this);
		removeView.addActionListener(this);
		chosenViewer.addActionListener(this);
		
		country.addActionListener(this);
		startYear.addActionListener(this);
		endYear.addActionListener(this);
	}
	

	
	

	public void actionPerformed(ActionEvent press) 
	{
		// if the recalculate button is pressed
		if (press.getSource() == recalculate) 
		{
			// check if all the choices are not null and are all valid
			if(!countryChoice.equals(null) && startYearChoice != -1 && endYearChoice != -1 && !analysisID.equals(null) && analysisCheck.allValid()) 
			{
				// create a selection object
				Selection input = new Selection();
				// set the user choices in the selection object
				input.setCountry(countryChoice);
				input.setStartYear(startYearChoice);
				input.setEndYear(endYearChoice);
				input.setAnalysis(analysisID);
				// create a comp server object
				ComputationServer cs = new ComputationServer();
				// set the selection object for comp server
				cs.setSelection(input);
				// create analysisstrat object with user chosen analysisID
				AnalysisStrategy analysis = AnalysisCreator.create(analysisID);
				// set analysis object for comp server
				cs.setStrategy(analysis);
				// run comp server strategy
				cs.runStrategy(plotDisplay, myResults);
				
			}
		}
		if(press.getSource() == analysis) 
		{
			// get analysis drop down menu box
			String newAnalysis = String.valueOf(analysis.getSelectedItem());
			System.out.println(newAnalysis);
			
			
			// Proceed if there is a change 
			if (this.analysisID != newAnalysis) {
				// If the existing analysis ID is not null, empty the viewers
				if (this.analysisID != null) {
					myResults.emptyViewers();
				}
				
				// Always set the new analysisID and create a new analysisDB
				this.analysisID = newAnalysis;
				analysisCheck = new AnalysisDB(newAnalysis);
			}
			

		}
		
		if (press.getSource() == addView) {
			String selectedViewer;
			selectedViewer = (String) chosenViewer.getSelectedItem();
			System.out.println(selectedViewer);
			
			ViewerCreator myCreator = new ViewerCreator();
			boolean valid = false;
			
			if (analysisCheck != null && analysisCheck.validViewer(selectedViewer)) {
				valid = true;
			}
			if (!valid) {
				chosenViewer.setBackground(Color.red);
			}
			
			if (selectedViewer.equals("Report")) {
				
				JTextArea report = new JTextArea();
				report.setText("Textual Report");
				
				myReport.setViewportView(report);
				
				// Call the viewer creator, create a new bar chart, and attach it 
				ViewerType type = ViewerType.REPORT;
				Viewer newViewer = myCreator.createViewer(type);
				
				
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
					ViewerType type = ViewerType.BARGRAPH;
					newViewer = myCreator.createViewer(type);
					newViewer.setPanel(myPanels.get(1));
					
				}else if(selectedViewer.equals("Scatter Chart")) {
					XYPlot plot = new XYPlot();
					chart = new JFreeChart("SCATTER PLOT", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

					// Display a temporary empty plot window
					myPanels.get(2).setChart(chart);
					
					// Call the viewer creator, create a new bar chart, and attach it 
					ViewerType type = ViewerType.SCATTERPLOT;
					newViewer = myCreator.createViewer(type);
					newViewer.setPanel(myPanels.get(2));
					
					
				}else if(selectedViewer.equals("Line Chart")) {
					XYPlot plot = new XYPlot();
					chart = new JFreeChart("LINE CHART",new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					
					// Display a temporary empty plot window
					myPanels.get(3).setChart(chart);
					
					// Call the viewer creator, create a new bar chart, and attach it 
					ViewerType type = ViewerType.LINEGRAPH;
					newViewer = myCreator.createViewer(type);
					
					newViewer.setPanel(myPanels.get(3));
					
				}else{
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					chart = ChartFactory.createMultiplePieChart("PIE CHART", dataset,TableOrder.BY_COLUMN, true, true, false);

					// Display a temporary empty plot window
					myPanels.get(4).setChart(chart);
					
					// Call the viewer creator, create a new bar chart, and attach it 
					ViewerType type = ViewerType.PIECHART;
					newViewer = myCreator.createViewer(type);
					
					newViewer.setPanel(myPanels.get(4));
				}
				myResults.attachViewer(newViewer);
				myViewers.add(newViewer);
				this.pack();
			}
		}
			
		// REMOVE BUTTON 
		if (press.getSource() == removeView) {
			String selectedViewer;
			selectedViewer = (String) chosenViewer.getSelectedItem();
			boolean valid = true;
			if (selectedViewer.equals("Report")) {
				JTextArea report = new JTextArea();
				myReport.setViewportView(report);
				
				// Call the viewer creator, create a new bar chart, and attach it 
				removeViewer(ViewerType.REPORT);
				
			}else {
				JFreeChart chart;
				if (selectedViewer.equals("Bar Chart")){
					CategoryPlot plot = new CategoryPlot();
					chart = new JFreeChart("", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					
					myPanels.get(1).setChart(chart);
					
					removeViewer(ViewerType.BARGRAPH);
				}else if(selectedViewer.equals("Scatter Chart")) {
					XYPlot plot = new XYPlot();
					chart = new JFreeChart("", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

					myPanels.get(2).setChart(chart);
					
					removeViewer(ViewerType.SCATTERPLOT);
				}else if(selectedViewer.equals("Line Chart")) {
					XYPlot plot = new XYPlot();
					chart = new JFreeChart("",new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					
					myPanels.get(3).setChart(chart);
					
					removeViewer(ViewerType.LINEGRAPH);
				}else{
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					chart = ChartFactory.createMultiplePieChart("", dataset,TableOrder.BY_COLUMN, true, true, false);
					
					myPanels.get(4).setChart(chart);
					
					valid = removeViewer(ViewerType.PIECHART);
				}
				if (valid) {
					chosenViewer.setBackground(Color.white);
				}

			}
		}
		if (press.getSource() == country) {
			// Perform check that the country is valid given the analysis 
			
			// Add the country to the 
		}
		
	}
	

	
	

	public boolean removeViewer(ViewerType type) {
		boolean valid = true;
		try {
			for (int i = 0 ; i < myViewers.size(); i++) {
				if (myViewers.get(i).getType() == type) {
					myResults.detachViewer(myViewers.get(i).getType());
					myViewers.remove(i);
				}
				if (!analysisCheck.validCountry(myViewers.get(i).name)) {
					valid = false;
				}
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return valid;
		
	}


	public static void main(String[] args) {

		JFrame frame = MainDisplay.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}
	// TODO Auto-generated method stub

}