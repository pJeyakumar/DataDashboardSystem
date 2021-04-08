package httpTest;

import java.io.*;
import java.util.ArrayList;

/**
 * @author - Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * @date - April 7, 2021
 * 
 * Analysis Strategy Classes Abstract
 * Functional Classes that:
 *   1. (Input) Gets fetched Data (calls a Reader Class that returns Data Objects)
 *   2. Handles the analysis and computation (if needed for an Analysis) on these Data Objects
 *   3. (Output) Populates this Processed Data to a Results Object
 * Called by
 *  - Computational Server class
 * Data:
 * - Attributes of Fetched Data
 * - Arrays to contain Processed Data and Related Years
 * Methods:
 * - Constructor that creates an instance of an object of this class
 * - Do Analysis: Varies per Analysis Strategy. Does the computations if needed
 *    , and fills processed data and year arrays
 * - Populate Results - Set current analysis to a Results object so it can access populated data
 * - Helper methods for getting averages and ratios
 */


abstract class AnalysisStrategy {
	
	// ---- Instance Variables ----
	
	// ---- NAMES ----
	// String to hold the full, formal name of this analysis 
	protected String analysisID; 
	// Array to hold the Strings that indicate the name of the series above | sent to results object
	protected String[] dataNames;    // i.e. "Mortality/1000"   "Methane Emissions (MT)"
	protected String[] axisNames;    // i.e. "$USD"  "Year" 
	
	// ---- DATA ----
	// Array to hold the ArrayList<Double> that have the processed data values | sent to results object
	protected ArrayList<Double>[] processedData; 
	// Array to hold the ArrayList<Integer> that have the years corresponding to the above data values | sent to results object
	protected ArrayList<Integer>[] years;
	// Integer value that indicates the number of series the analysis deals with | sent to results object
	protected int numOfSeries;
	
	// Constructor - to be created by Computational Server class
	public AnalysisStrategy() {}
	
	/** Do Analysis Method
	* To be called from Computational Server class
	* Core method of the project that, based on the user selection:
	* 1. Gets unprocessed data from Reader class
	* 2. Processes the data if need be
	* 3. Populates Processed Data and Years array
	* @param Selection - user choices
	*/
	public abstract void doAnalysis(Selection selection);

	
	/**
	 * Retrieve Data Method
	 * To be called from Do Analysis to get the data from the world bank to be processed
	 * @return Data - a single Data object
	 */
	public abstract Data[] retrieveData(Selection selection);
	
	/**
	 * Populate Results Method
	 * @param res A Results object
	 * @output
	 * 	- Attach current analysis to this results object so it may access the processed data
	 */
	public void populateResults(Results res) {
		res.attachAnalysis(this);
	}
	
	/*
	 * Helper method to get an average (mean) from a list of values
	 */
	public ArrayList<Double> getAverage(ArrayList<Double> values) 
	{
		// Initialize average variable, number of values and sum of the values
		ArrayList<Double> averages = new ArrayList<Double>();
		double average = 0;
		int numValues = values.size();
		double sum = 0;
		
		// Iterate over length of values array
		for (int i = 0; i < values.size(); i++) {
			sum += values.get(i).doubleValue();
		}
		
		// Calculate average through division, add to array to return and return the array
		average = sum / numValues;
		averages.add(average);
		return averages;
		
	} // End Get Average
	
	/*
	 * Helper method to get a list of ratios between two lists of values
	 */
	public ArrayList<Double> getRatios(ArrayList<Double> numVal, ArrayList<Double> denoVal) 
	{
		// Creating ArrayList<Double> variable
		ArrayList<Double> ratioVals = new ArrayList<Double>();
		// For loop to iterate through all the values of both ArrayLists
		for(int i = 0; i < numVal.size(); i++)
		{
			// If either value from one of the ArrayLists is 0 (meaning there originally was a NULL value), we will store -1.0 in the final ArrayList
			// so that in the Viewer class, it will skip this Year-Value set
			if(numVal.get(i) == 0 || denoVal.get(i) == 0)
			{
				ratioVals.add(i, -1.0);
			}
			// Otherwise add the num/deno value in normally
			else
			{
				ratioVals.add(i, numVal.get(i)/denoVal.get(i));
			}
		}
		
		return ratioVals;		
	} 
	
	/**
	 * Following methods used as getters in the "Result" class
	 */
	public ArrayList<Double>[] getProcessedData() {
		return this.processedData;
	}
	public ArrayList<Integer>[] getYears() {
		return this.years;
	}
	public String[] getDataNames() {
		return this.dataNames;
	}
	public String[] getAxisNames() {
		return this.axisNames;
	}
	public String getAnalysisID() {
		return this.analysisID;
	}
	
} // End Class
