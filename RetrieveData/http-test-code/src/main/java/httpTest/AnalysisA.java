package httpTest;
/*
 * NAME: Piranavan Jeyakumar
 * DATE: 2021-04-02
 * DESCRIPTION: Analysis for Renewable electricity output (% of total electricity output) vs Renewable energy consumption (% of total final energy consumption)
 * 				No computations are needed, this class will retrieve the values from the Reader class and sort them into the array instance variables
 */
import java.util.ArrayList;

public class AnalysisA extends AnalysisStrategy
{
	
	public AnalysisA() 
	{
		
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
	@SuppressWarnings("unchecked") 		// pretty ugly and annoying but we need this as an array so that it will be easier to access later
	public void doAnalysis(Selection selection) 
	{
		// Variable Declarations
		this.processedData = new ArrayList[2];
		this.years = new ArrayList[2];
		this.dataNames = new String[2];
		
		// Getting array of Data objects from Reader class
		Data[] finalData = retrieveData(selection);
	
		for(int i = 0; i < 2; i++)
		{
			// Storing the ArrayList<Double> into the Array | we will get an ARRAY storing ArrayList<Double>, which are the data values
			this.processedData[i] = finalData[i].getFirst();
			// Storing the ArrayList<Integer> into the Array | we will get an ARRAY storing ArrayList<Integer>, which are the years
			this.years[i] = finalData[i].getSecond();
			// Storing the Strings into the Array | we will get an ARRAY storing Strings, which are the name of the data series
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
			// Create an instance of the Reader class
			Reader reader = new Reader();
			// Get Data object for Renewable electricity output (% of total electricity output) P.I
			Data series1 = reader.retrieveData(selection, "EG.ELC.RNEW.ZS");
			// Get Data object for Renewable energy consumption (% of total final energy consumption) P.I
			Data series2 = reader.retrieveData(selection, "EG.FEC.RNEW.ZS");
			// Create an Array of Data objects * hard-coded to be size 2 since this is a 2-series analysis*
			Data[] seriesArray = new Data[2];
			// Put the Data objects into the Data objects Array
			seriesArray[0] = series1;
			seriesArray[1] = series2;
			// Return Data objects Array to caller
			return seriesArray;
			
	}
}
