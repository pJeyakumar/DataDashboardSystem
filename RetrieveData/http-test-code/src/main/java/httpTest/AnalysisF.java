package httpTest;

import java.util.ArrayList;

public class AnalysisF extends AnalysisStrategy
{
	
	
	public AnalysisF()
	{
		this.numOfSeries = 3; 
		this.analysisID = "Total GHG Emissions vs % Urban Population vs % Fossil Fuel Energy Consumption";
		
		this.dataNames = new String[3];
		this.dataNames[0] = "Total GHG Emissions (MT CO2 eqv)";
		this.dataNames[1] = "% Urban Population (% Total Population)";
		this.dataNames[2] = "% Fossil Fuel Consumption (% Total Energy)";
		
		this.axisNames = new String[2];
		this.axisNames[0] =  "Megatons (CO2 Equivalent)";
		this.axisNames[1] =  "% Total";
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
		
		double[] divisionFactor = {1000.0, 1.0, 1.0};
		
		ArrayList<Double> temp; 
		// Storing the ArrayList<Double> into the Array | we will get an ARRAY storing ArrayList<Double>, which are the data values
		for (int i = 0 ; i < dataSets.length; i++) {
			
			temp = dataSets[i].getFirst();
			
			for (int j = 0; j < temp.size(); j++) {
				temp.set(j, (temp.get(j)) / divisionFactor[i]);
			}
			
			this.processedData[i] = temp;
			this.years[i] = dataSets[i].getSecond();
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
		
		String[] urlNames = {"EN.ATM.GHGT.KT.CE", "SP.URB.TOTL.IN.ZS","EG.USE.COMM.FO.ZS"};
		
		Data[] seriesArray = new Data[3];
		
		for (int i = 0; i < 3 ; i ++) {
			seriesArray[i] = reader.retrieveData(selection, urlNames[i]);
		}

		// Return Data objects Array to caller
		return seriesArray;
		
	}
}
