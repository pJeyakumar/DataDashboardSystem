package httpTest;

import java.util.ArrayList;

/*
 * Name: Allan Zhang
 * Analysis: Ratio of Agricultural Land vs Forest Land
 * Description: Use the indicators: Agricultural Land (% of land area); Forest Land (% of Land Area) 
 * to compute a ratio computation of agricultural land vs forest land for respective nations within their respective years.
 */

public class AnalysisG extends AnalysisStrategy
{
	
	public AnalysisG() 
	{
		this.numOfSeries = 1;
		this.analysisID = "Ratio of agricultural land (% of total area) vs forest land (%)";
		
		this.dataNames = new String[1];
		this.dataNames[0] = "Ratio of Agricultural vs Forest Land";
		
		this.axisNames = new String[1];
		this.axisNames[0] = "Ratio";

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
	public void doAnalysis(Selection selection)
	{
		// variable declarations
		this.processedData = new ArrayList[1];
		this.years = new ArrayList[1];
		
		// getting array of data objects from reader class
		Data[] finalData = retrieveData(selection);
		
		// perform ratio computation on both series, store in processedData array
		this.processedData[0] = this.getRatios(finalData[0].getFirst(), finalData[1].getFirst());
		
		for(int i = 0; i < 1; i++) {
			// get Array of ArrayList<Integer> containing years
			this.years[i] = finalData[i].getSecond();
			// get Array storing Strings, which are name of data series
		}
		
		this.dataNames[0] = "Ratio of Agricultural Land to Forest Land";
	}
	
	
	/**
	 * Retrieve Data
	 * To be called from Do Analysis to get the data to be processed
	 * @return Data
	 */
	public Data[] retrieveData(Selection selection) 
	{
		// crate instance of Reader class
		Reader reader = new Reader();
		
		// get Data object for Agricultural land data
		Data series1 = reader.retrieveData(selection,  "AG.LND.AGRI.ZS");
		// get Data object for Forest land data
		Data series2 = reader.retrieveData(selection,  "AG.LND.FRST.ZS");
		
		// create array of Data objects, size 2 since 2-series
		Data[] seriesArray = new Data[2];
		
		// put data objects into their respective Data objects Array
		seriesArray[0] = series1;
		seriesArray[1] = series2;
		
		// return Data objects Array to caller
		return seriesArray;
	}
}
