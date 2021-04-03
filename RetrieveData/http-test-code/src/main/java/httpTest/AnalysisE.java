package httpTest;

import java.util.ArrayList;

public class AnalysisE extends AnalysisStrategy
{
	
	
	public AnalysisE() 
	{
		this.numOfSeries = 3; 
	}
	
	
	/** Do Analysis Method
	* To be called from Computational Server class
	* Core method of the project that, based on the user selection:
	* 1. Gets unprocessed data from Reader class
	* 2. Processes this data (not in this case)
	* 3. Separates the data values, years and names and group them into their own arrays (which will be sent to the results object)
	* 
	* @param Selection
	* 
	*/
	public void doAnalysis(Selection selection) 
	{
		// Variable Declarations
		this.processedData = new ArrayList[3];
		this.years = new ArrayList[3];
		this.dataNames = new String[3];
		
		// Getting array of Data objects from Reader class
		Data[] finalData = this.retrieveData(selection);
		
		// Storing the ArrayList<Double> into the Array | we will get an ARRAY storing ArrayList<Double>, which are the data values
		for (int i = 0 ; i < finalData.length; i++) {
			this.processedData[i] = finalData[i].getFirst();
			this.years[i] = finalData[i].getSecond();
			this.dataNames[0] = finalData[i].getTypeA();
		}
				
		// this.units =['kwh', 'kwh', '%']
	}
	
	
	/**
	 * Retrieve Data
	 * To be called from Do Analysis to get the data to be processed
	 * @return Data
	 */
	public Data[] retrieveData(Selection selection) 
	{
		// Create an instance of the Reader class
		Reader reader = new Reader();
		
		String[] analyses = {"EN.ATM.NOXE.AG.KT.CE","EN.ATM.METH.AG.KT.CE",  "AG.LND.AGRI.ZS"};
		
		// Get Data object for Renewable electricity output (% of total electricity output) P.I
		Data[] seriesArray = new Data[3];
		
		for (int i = 0; i < 3 ; i ++) {
			seriesArray[i] = reader.retrieveData(selection, analyses[i]);
		}

		// Return Data objects Array to caller
		return seriesArray;
		
	}
}
