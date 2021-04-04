package httpTest;

import java.io.*;
import java.util.ArrayList;

/**
 * @author - Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
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
 * - Do Analysis: Varies per Analysis Strategy. Does the computations if needed
 *    , and fills processed data and year arrays
 * - Populate Results - Set Data organized to a Results object
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
		res.setData(this.processedData, this.years, this.dataNames, this.axisNames, this.analysisID);
	}
	
	/*
	 * Helper method to get an average (mean) from a list of values
	 */
	public double getAverage(ArrayList<Double> values) 
	{
		// Initialize average variable, number of values and sum of the values
		double average = 0;
		int numValues = values.size();
		double sum = 0;
		
		// Iterate over length of values array
		for (int i = 0; i < values.size(); i++) {
			sum += values.get(i).doubleValue();
		}
		
		average = sum / numValues;
		return average;
		
	} // End Get Average
	
	/*
	 * Helper method to get a bunch of ratios between two lists of values
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
	} // End Get Ratios
	
} // End Class
