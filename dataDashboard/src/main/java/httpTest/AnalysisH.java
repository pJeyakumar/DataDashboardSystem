package httpTest;

import java.util.ArrayList;

/*
 * Name: Allan Zhang
 * Analysis: Average Agricultural Land over a Time Range
 * Description: Use the indicator, Agricultural Land (% of land area)
 * to compute its average value for a nation over a given time range.
 */

public class AnalysisH extends AnalysisStrategy
{
	
	
	public AnalysisH() {
		this.numOfSeries = 1;
		this.analysisID = "Average agricultural land (% of land area)";
		
		this.dataNames = new String[2];
		this.dataNames[0] = "Average Agricultural Land %";
		this.dataNames[1] = "Average Other Land %";
		
		this.axisNames = new String[1];
		this.axisNames[0] = "";
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
		this.years = new ArrayList[1];  // more so just a range for the average
		
		// get array of Data objects from Reader class
		Data[] finalData = retrieveData(selection);
		
		// obtain the average agricultural land for all the years, store in a DoubleArray<Double> for consistency w/ other analysis methods
		this.processedData[0] = this.getAverage(finalData[0].getFirst());

		// get the time range in an array
		this.years[0] = finalData[0].getSecond();
		
	}
	
	
	/**
	 * Retrieve Data
	 * To be called from Do Analysis to get the data to be processed
	 * @return Data
	 */
	public Data[] retrieveData(Selection selection)
	{
		// create instance of Reader class
		Reader reader = new Reader();
		
		// get Data object for Agricultural land
		Data series1 = reader.retrieveData(selection, "AG.LND.AGRI.ZS");
		
		// create array of Data objects, 1-series analysis, so size 1
		Data[] seriesArray = new Data[1];
		
		// put Data objects into Data objects Array
		seriesArray[0] = series1;
		
		// return Data objects Array to caller
		return seriesArray;
	}
}
