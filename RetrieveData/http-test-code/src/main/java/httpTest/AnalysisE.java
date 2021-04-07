package httpTest;

import java.util.ArrayList;

public class AnalysisE extends AnalysisStrategy
{
	
	
	public AnalysisE() 
	{
		this.numOfSeries = 3; 
		this.analysisID = "Agricultural Land vs NO2 Emissions vs Methane Emissions";
		
		this.dataNames = new String[3];
		
		this.axisNames = new String[2];
		this.axisNames[0] = "% Total Land";
		this.axisNames[1] = "Megatons";
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
		
		// Getting array of Data objects from Reader class
		Data[] dataSets = this.retrieveData(selection);
		
		double[] divisionFactor = {1.0, 1000.0, 1000.0};
		
		ArrayList<Double> temp; 
		// Storing the ArrayList<Double> into the Array | we will get an ARRAY storing ArrayList<Double>, which are the data values
		for (int i = 0 ; i < dataSets.length; i++) {
			
			temp = dataSets[i].getFirst();
			
			for (int j = 0; j < temp.size(); j++) {
				temp.set(j, (temp.get(j)) / divisionFactor[i]);
			}
			
			this.processedData[i] = temp;
			this.years[i] = dataSets[i].getSecond();
			this.dataNames[i] = dataSets[i].getTypeA(); 
		}
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
		
		// VALID TIME RANGE 
		// 1985 : 2015
		String[] urlNames = {"AG.LND.AGRI.ZS", "EN.ATM.NOXE.AG.KT.CE","EN.ATM.METH.AG.KT.CE"};
		// MISSING VALUES FOR FIRST 5 FOR GERMANY
		// Get Data object for Renewable electricity output (% of total electricity output) P.I
		Data[] seriesArray = new Data[3];
		
		for (int i = 0; i < 3 ; i ++) {
			seriesArray[i] = reader.retrieveData(selection, urlNames[i]);
		}

		// Return Data objects Array to caller
		return seriesArray;
		
	}
}
