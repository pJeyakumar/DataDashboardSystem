package httpTest;

import java.util.ArrayList;

public class AnalysisH extends AnalysisStrategy
{
	
	
	public AnalysisH() {
		this.numOfSeries = 1;
		this.analysisID = "Agricultural land (% of land area)";
		
		this.dataNames = new String[2];
		this.dataNames[0] = "Average Agricultural Land %";
		this.dataNames[1] = "Average Other Land %";
		
		// no axisNames given its a pie chart??
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
		this.processedData = new ArrayList[1];
		this.years = new ArrayList[1];  // more so just a range for the average
		
		Data[] finalData = retrieveData(selection);
		
		this.processedData[0] = finalData[0].getFirst();
		this.years[0] = finalData[0].getSecond();
		
		// getAverage?
	}
	
	
	/**
	 * Retrieve Data
	 * To be called from Do Analysis to get the data to be processed
	 * @return Data
	 */
	public Data[] retrieveData(Selection selection)
	{
		Reader reader = new Reader();
		
		Data series1 = reader.retrieveData(selection, "AG.LND.AGRI.ZS");
		
		Data[] seriesArray = new Data[1];
		seriesArray[0] = series1;
		
		return seriesArray;
	}
}
