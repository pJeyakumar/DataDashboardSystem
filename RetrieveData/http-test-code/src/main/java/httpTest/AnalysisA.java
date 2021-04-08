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
	/*DESCRIPTION: Constructor method
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */
	public AnalysisA() 
	{
		
	}
	
	/* DESCRIPTION:  Method for performing AnalysisA, given the Selection object containing the user choices, this method will call the retrieveData
	 * 				method and place the values, years and dataNames into arrays. The method also sets the analysisID that will be used as well as units
	 * INPUT: Selection
	 * OUTPUT: N/A
	 */
	@SuppressWarnings("unchecked") 		// pretty ugly and annoying but we need this as an array so that it will be easier to access later
	public void doAnalysis(Selection selection) 
	{
		// Variable Declarations
		System.out.println("PERFORMING ANALYSIS A...");
		this.processedData = new ArrayList[2];
		this.years = new ArrayList[2];
		this.dataNames = new String[2];
		this.axisNames = new String[1];
		
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
		
		// Setting AnalysisID
		this.analysisID = "Renewable electricity output vs Renewable energy consumption";
		// Setting Units
		this.axisNames[0] = "%";
	}
	
	/* DESCRIPTION: Method that will create a Reader object and get the data specified by the user selections and analysis CODE
	 * 				it will then put the retrieved data objects into an array of data objects and return this array
	 * INPUT: Selection
	 * OUTPUT: Data[]
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
