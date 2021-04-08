package httpTest;
import java.util.ArrayList;

/*
 * NAME: John Palmer
 * DATE: 2021-04-07
 * DESCRIPTION: Analysis strategy subclass responsible for Total GHG Emissions vs % Urban Population vs % Fossil Fuel Energy Consumption
 */

public class AnalysisF extends AnalysisStrategy
{
	
	/**
	* Constructor for a new Analysis F object
	*/
	public AnalysisF()
	{
		
		this.numOfSeries = 3; 
		this.analysisID = "Total GHG Emissions vs % Urban Population vs % Fossil Fuel Energy Consumption";
		
		// Hard coded because units have been converted 
		this.dataNames = new String[3];
		this.dataNames[0] = "Total greenhouse gas emissions (MT of CO2 equivalent)";
		this.dataNames[1] = "Urban population (% of total population) ";
		this.dataNames[2] = "Fossil fuel energy consumption (% of total energy)";
		
		// Create new custom axis names 
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
		for (int i = 0 ; i < dataSets.length; i++) {
			
			// Retrieve the double array from the data object
			temp = dataSets[i].getFirst();
			
			// Convert every value by the division factor
			for (int j = 0; j < temp.size(); j++) {
				temp.set(j, (temp.get(j)) / divisionFactor[i]);
			}
			
			// Load the final data into processed data, along with the years (unprocessed)
			this.processedData[i] = temp;
			this.years[i] = dataSets[i].getSecond();
		}
	}
	
	
	/**
	 * To be called from Do Analysis to get the data to be processed
	 * @param selection The selection object to be used to make requests to the reader  
	 * @return Data[] An array of data objects
	 */
	public Data[] retrieveData(Selection selection) 
	{
		// Create an instance of the Reader class
		Reader reader = new Reader();
		
		// Analysis names in the URL 
		String[] urlNames = {"EN.ATM.GHGT.KT.CE", "SP.URB.TOTL.IN.ZS","EG.USE.COMM.FO.ZS"};
		
		// Prepare and load an array of data objects (3 in this case)
		Data[] seriesArray = new Data[3];
		for (int i = 0; i < 3 ; i ++) {
			seriesArray[i] = reader.retrieveWorldData(selection, urlNames[i]);
		}

		// Return Data objects Array to caller
		return seriesArray;
		
	}
}
