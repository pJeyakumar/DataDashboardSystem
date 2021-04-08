package httpTest;
import java.util.ArrayList;

/**
 * @author Shoumik Shill
 * 
 * Analysis Strategy for Analysis C:
 *  - 1-series
 *  - AG.LND.FRST.ZS
 *  - Average of Forest area (% of land area) from 1995 to 2015
 *  - To be a Pie chart that consists of:
 *    - Average of Forest area
 *    - Other land area
 */

public class AnalysisC extends AnalysisStrategy
{
	/*
	 * Constructor to initialize number of series, analysisID, data names, axis names
	 */
	public AnalysisC() 
	{
		this.numOfSeries = 1;
		this.analysisID = "Forest area (% of land area)";
		
		this.dataNames = new String[2];
		this.dataNames[0] = "Average Forest area";
		this.dataNames[1] = "Other land area";
		
		this.axisNames = new String[1];
		this.axisNames[0] = "";
	}
	
	
	/** Do Analysis Method
	* To be called from Computational Server class
	* Based on the user selection
	* 1. Gets unprocessed data from Reader class by calling retrieveData(selection)
	* 2. Process data (in this case, get the average of the list of unprocessed data)
	* 3. Fill in processed data and years arrays
	* @param selection
	* @output Fill in processed data and years arrays
	*/
	public void doAnalysis(Selection selection) {
		
		// Initialize processed data and years arrays
		this.processedData = new ArrayList[1];
		this.years = new ArrayList[1];
		
		// Get World Bank data and get the values
		Data[] dataSet = this.retrieveData(selection);
		ArrayList<Double> forestData = dataSet[0].getFirst();
		
		// Calculate average and put it in an Array
		ArrayList<Double> avgForest = this.getAverage(forestData); // Array that has an element with the average

		// Fill in processed data and year arrays
		this.processedData[0] = avgForest;
		this.years[0] = dataSet[0].getSecond();
	} // End Do Analysis C
	
	
	/**
	 * Retrieve Data
	 * To be called from Do Analysis to get the data to be processed
	 * @return Data from World Bank, based on @param selection / user choices
	 */
	public Data[] retrieveData(Selection selection) 
	{
		// Initialize Data Array to be returned
		Data[] seriesArray = new Data[1];
		
		// Create a reader and retrieve data based on selection and Analysis
		Reader reader = new Reader();
		seriesArray[0] = reader.retrieveWorldData(selection, "AG.LND.FRST.ZS");
		
		// Return data array
		return seriesArray;
	} // End Retrieve Data
	
} // End Analysis C Class
