package httpTest;
import java.util.ArrayList;

/**
 * @author Shoumik Shill
 * 
 * Analysis Strategy for Analysis C:
 *  - 1-series
 *  - AG.LND.FRST.ZS
 *  - Average of Forest area (% of land area) in Canada from 1990 to 2018
 *  - Pie chart:
 *    - Average of Forest area
 *    - Other land area
 */

public class AnalysisC extends AnalysisStrategy
{
	
	
	public AnalysisC() 
	{
		this.numOfSeries = 1;
		this.analysisID = "Forest area (% of land area)";
		
		this.dataNames = new String[2];
		this.dataNames[0] = "Average Forest area in Canada";
		this.dataNames[1] = "Other land area";
		
		// ?
		this.axisNames = new String[2];
		this.axisNames[0] = "Forest area %";
		this.axisNames[1] = "Other land";
	}
	
	
	/** Do Analysis Method
	* To be called from Computational Server class
	* Core method of the project that, based on the user selection:
	* 1. Gets unprocessed data from Reader class
	* 2. 
	* 
	* @param Selection
	* 
	*/
	public void doAnalysis(Selection selection) {
		
		this.processedData = new ArrayList[1];
		
		this.years = new ArrayList[28];
		
		
		
		
		
	}
	
	
	/**
	 * Retrieve Data
	 * To be called from Do Analysis to get the data to be processed
	 * @return Data from World Bank
	 */
	public Data[] retrieveData(Selection selection) 
	{
		// Initialize Data Array to be returned
		Data[] seriesArray = new Data[1];
		
		// Create a reader and retrieve data based on selection and Analysis
		Reader reader = new Reader();
		seriesArray[0] = reader.retrieveData(selection, "AG.LND.FRST.ZS");
		
		// Return data
		return seriesArray;
		
	} // End Retrieve Data
	
} // End Analysis C Class
