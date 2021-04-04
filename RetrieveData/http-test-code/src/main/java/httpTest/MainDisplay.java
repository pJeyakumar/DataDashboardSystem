package httpTest;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

public class MainDisplay extends JFrame{
	/**
	 * 
	 */
	
	private Results myResults;  
	
	private ArrayList<Viewer> myViewers;
	
	private ArrayList<ChartPanel> myPanels;
	
	private static final long serialVersionUID = 1L;
	
	private final JPanel plotDisplay;
	private static MainDisplay instance;

	public static MainDisplay getInstance() {
		if (instance == null)
			instance = new MainDisplay();

		return instance;
	}
	
	public String AnalysisID;

	private MainDisplay() {
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
		JComboBox<String> countriesList = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		for (int i = 2021; i >= 2010; i--) {
			years.add("" + i);
		}
		JComboBox<String> fromList = new JComboBox<String>(years);
		JComboBox<String> toList = new JComboBox<String>(years);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		JButton recalculate = new JButton("Recalculate");
		recalculate.addActionListener(new recompute());

		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		
		JButton addView = new JButton("+");
		
		addView.addActionListener(new addViewer(viewsList, plotDisplay));
		JButton removeView = new JButton("-");

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("Mortality");
		methodsNames.add("Mortality vs Expenses");
		methodsNames.add("Mortality vs Expenses & Hospital Beds");
		methodsNames.add("Mortality vs GDP");
		methodsNames.add("Unemployment vs GDP");
		methodsNames.add("Unemployment");

		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel plotDisplay = new JPanel();
		plotDisplay.setLayout(new GridLayout(2, 0));
		
		myViewers = new ArrayList<Viewer>();
		myPanels = new ArrayList<ChartPanel>();
		
		
		for (int i = 0 ; i < 6; i++) {
			CategoryPlot plot = new CategoryPlot();
			JFreeChart chart = new JFreeChart("", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			
			myPanels.add(chartPanel);
			plotDisplay.add(chartPanel);
		}
		

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(plotDisplay, BorderLayout.WEST);
	}

	class addViewer implements ActionListener{
		
		public String selectedViewer;
		public JPanel plotDisplay;
		public JComboBox<String> dropdown;
		public String choice;
		public addViewer(JComboBox<String> viewsList, JPanel plot) {
			dropdown = viewsList;
			plotDisplay = plot;
		}
		public void actionPerformed(ActionEvent actionEvent) {
			selectedViewer = (String) dropdown.getSelectedItem();
			System.out.println(selectedViewer);
			
			
			ViewerCreator myCreator = new ViewerCreator();
			
			if (selectedViewer.equals("Report")) {
				JTextArea report = new JTextArea();
				JScrollPane chart = new JScrollPane(report);
				
			}else {
				ChartPanel chartPanel;
				if (selectedViewer.equals("Bar Chart")){
					CategoryPlot plot = new CategoryPlot();
					JFreeChart chart = new JFreeChart("HELLO THERE", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					myPanels.get(2).setChart(chart);
					
					
					// Call the viewer creator, create a new bar chart, and attach it 
					ViewerType type = ViewerType.BARGRAPH;
					myViewers.add(myCreator.createViewer(type));
				}else if(selectedViewer.equals("Scatter Chart")) {
					XYPlot plot = new XYPlot();
					JFreeChart chart = new JFreeChart("MY NAME IS", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					myPanels.get(3).setChart(chart);
				}else if(selectedViewer.equals("Line Chart")) {
					XYPlot plot = new XYPlot();
					JFreeChart chart = new JFreeChart("JOHN",new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					myPanels.get(4).setChart(chart);
				}else{
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					JFreeChart chart = ChartFactory.createMultiplePieChart("Unemployment: Men vs Women", dataset,TableOrder.BY_COLUMN, true, true, false);
					myPanels.get(5).setChart(chart);
				}

			}
				
	    }
	}
	
	class removeViewer implements ActionListener{
		
		public String selectedViewer;
		public JPanel plotDisplay;
		public JComboBox<String> dropdown;
		public String choice;
		public removeViewer(JComboBox<String> viewsList, JPanel plot) {
			dropdown = viewsList;
			plotDisplay = plot;
		}
		public void actionPerformed(ActionEvent actionEvent) {
			selectedViewer = (String) dropdown.getSelectedItem();
			System.out.println(selectedViewer);
			if (selectedViewer.equals("Report")) {
				JTextArea report = new JTextArea();
				JScrollPane chart = new JScrollPane(report);
				
			}else {
				ChartPanel chartPanel;
				if (selectedViewer.equals("Bar Chart")){
					CategoryPlot plot = new CategoryPlot();
					JFreeChart chart = new JFreeChart("HELLO THERE", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					myPanels.get(2).setChart(chart);
				}else if(selectedViewer.equals("Scatter Chart")) {
					XYPlot plot = new XYPlot();
					JFreeChart chart = new JFreeChart("MY NAME IS", new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					myPanels.get(3).setChart(chart);
				}else if(selectedViewer.equals("Line Chart")) {
					XYPlot plot = new XYPlot();
					JFreeChart chart = new JFreeChart("JOHN",new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
					myPanels.get(4).setChart(chart);
				}else{
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					JFreeChart chart = ChartFactory.createMultiplePieChart("Unemployment: Men vs Women", dataset,TableOrder.BY_COLUMN, true, true, false);
					myPanels.get(5).setChart(chart);
				}

			}
				
	    }
		
			
	
	}
	
	class recompute implements ActionListener{
		
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Hello there");
	    }
	}

	public static void main(String[] args) {

		JFrame frame = MainDisplay.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}
	// TODO Auto-generated method stub

}
