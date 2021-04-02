package httpTest;

import java.io.*;
import java.util.ArrayList;

/**
 * @author - Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * 
 * Analysis Strategy Class
 * Functional Class that:
 *   1. (Input) Gets fetched Data (calls a Reader Class that returns Data Object)
 *   2. Handles the analysis and computation (if needed for an Analysis) on these Data Objects
 *   3. (Output) Populates this Processed Data to a Results Object
 * Called by
 *  - Computational Server class
 * Data:
 * - Reader - the reader to get original data from
 * - Results - the
 */


abstract class AnalysisStrategy {
	
	AnalysisStrategy A = AnalysisCreator.create("A");
	
	// Reader class which will return unprocessed Data objects
	private Reader reader;
	
	// ?
	// maybe initialize Results object earlier
	// Results object which will be populated with processed Data objects
	// private Results results;
	
	// Array to hold the ArrayList<Double> that have the processed data values | sent to results object
	protected ArrayList<Double>[] processedData; 
	// Array to hold the ArrayListZ<Integer> that have the years corresponding to the above data values | sent to results object
	protected ArrayList<Integer>[] years;
	// Array to hold the Strings that indicate the name of the series above | sent to results object
	protected String[] dataNames;
	// Integer value that indicates the number of series the analysis deals with | sent to results object
	protected int numOfSeries; 
	

	// Based on number of series for a selection, get numOfSeries Data objects from Reader
	// (i.e. call Reader numOfSeries times)
	/*
	private Data[] data;
	private Data[] processedData;
	*/
	
	// Constructor - to be created by Computational Server class
	public AnalysisStrategy() {
		
		// Initialize Reader
		this.reader = new Reader();
	}
	
	/** Do Analysis Method
	* To be called from Computational Server class
	* Core method of the project that, based on the user selection:
	* 1. Gets unprocessed data from Reader class
	* 2. 
	* 
	* @param Selection - has getAnalysis, getAnalysis, getCountry, getStartYr, getEndYr
	* 
	*/
	public abstract void doAnalysis(Selection selection);

		/*
		// Get the Data from Reader, store in tempData
		Data tempData = retrieveData(selection);
		
		// PLACEHOLDER
		ArrayList<Double> pData1 = new ArrayList<Double>();
		this.processedDataArray[0] = new Data(pData1);
		
		populateResults(this.processedDataArray[0]);
		*/
	 // End Do Analysis Method

	
	/**
	 * Retrieve Data Method
	 * To be called from Do Analysis to get the data to be processed
	 * @return Data - a single Data object
	 */
	public abstract Data[] retrieveData(Selection selection);
		//return this.reader.retrieveData(selection);
	 // End Retrieve Data method
	
	/**
	 * Populate Results Method
	 * @input Data to be Processed (Array)
	 * @output
	 * 	- Populate Results Object
	 */
	public void populateResults(Results res) {
		// res.setData(this.processedData);
	}
	
	/**
	 * Not abstract because all inherited Analysis types may use it
	 * @param value1
	 * @param value2
	 * @return
	 */
	public double getAverage(double value1, double value2) {
		
	}
	
	/**
	 * Not abstract because all inherited Analysis types may use it
	 * @param value1
	 * @param value2
	 * @return
	 */
	public double getRatio(double value1, double value2) {
		
	}
	
	
} // END CLASS
