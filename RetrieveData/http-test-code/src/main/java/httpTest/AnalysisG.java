package httpTest;

import java.util.ArrayList;

public class AnalysisG extends AnalysisStrategy
{
	// skeleton TBC
	
	public AnalysisG() 
	{
		this.numOfSeries = 1;
		this.analysisID = " (ratio)";
		
		this.dataNames = new String[1];
		this.dataNames[0] = "Ratio of Agricultural vs Forest Land";
		
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
		this.processedData = new ArrayList[2];
		this.years = new ArrayList[2];
		this.dataNames = new String[2];
		
		Data[] finalData = retrieveData(selection);
		
		this.processedData[0] = this.getRatios(finalData[0].getFirst(), finalData[1].getFirst());
		
		for(int i = 0; i < 2; i++) {
			
			this.years[i] = finalData[i].getSecond();
			
			this.dataNames[i] = finalData[i].getTypeA();
		}
	}
	
	
	/**
	 * Retrieve Data
	 * To be called from Do Analysis to get the data to be processed
	 * @return Data
	 */
	public Data[] retrieveData(Selection selection) 
	{
		Reader reader = new Reader();
		
		Data series1 = reader.retrieveData(selection,  "AG.LND.AGRI.ZS");
		Data series2 = reader.retrieveData(selection,  "AG.LND.FRST.ZS");
		
		Data[] seriesArray = new Data[2];
		
		seriesArray[0] = series1;
		seriesArray[1] = series2;
		
		return seriesArray;
	}
}
