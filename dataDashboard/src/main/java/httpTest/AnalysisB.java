package httpTest;

import java.util.ArrayList;

/*
 * NAME: Piranavan Jeyakumar
 * DATE: 2021-04-03
 * DESCRIPTION: Analysis for Electricity production from coal sources (% of total) : Renewable electricity output (% of total electricity output) ratio 
 * 				Ratio computations are performed for this analysis, this class will retrieve the values from the Reader class and sort them into the array instance variables.
 */
public class AnalysisB extends AnalysisStrategy
{
	/* DESCRIPTION: Constructor method
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */
	public AnalysisB() 
	{
		
	}
	
	/*DESCRIPTION:  Method for performing AnalysisB, given the Selection object containing the user choices, this method will call the retrieveData
	 * 				method, perform the Ratio computations on the data, and place the values, years and dataNames into arrays. 
	 * 				The method also sets the analysisID that will be used as well as units
	 * INPUT: Selection
	 * OUTPUT: N/A
	 */
	@SuppressWarnings("unchecked")
	public void doAnalysis(Selection selection)
	{
		// Variable Declaration
		this.processedData = new ArrayList[1];
		this.years = new ArrayList[2];
		this.dataNames = new String[2];
		this.axisNames = new String[1];
		
		// Getting array of Data objects from Reader class
		Data[] finalData = retrieveData(selection);
		
		// Performing Ratio computation on both of the series (this returning 1 ArrayList<Double>) and storing it into the processedData Array
		this.processedData[0] = this.getRatios(finalData[0].getFirst(), finalData[1].getFirst());
		for(int i = 0; i < 2; i++)
		{
			// Storing the ArrayList<Integer> into the Array | we will get an ARRAY storing ArrayList<Integer>, which are the years
			this.years[i] = finalData[i].getSecond();			
		}
		
		// Storing the Strings into the Array | we will get an ARRAY storing Strings, which are the name of the data series
		this.dataNames[0] = "Coal Source Electricity production : Renewable electricity output";
		this.dataNames[1] = dataNames[0];
		// Setting AnalysisID
		this.analysisID = "Ratio of Electricity production from coal sources vs Renewable electricity output";
		// Setting Units
		this.axisNames[0] = "";
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
			// Get Data object for Electricity production from coal sources (% of total) P.I
			Data series1 = reader.retrieveData(selection, "EG.ELC.COAL.ZS");
			// Get Data object for Renewable electricity output (% of total electricity output)  P.I
			Data series2 = reader.retrieveData(selection, "EG.ELC.RNEW.ZS");
			// Create an Array of Data objects * hard-coded to be size 2 since this is a 2-series analysis*
			Data[] seriesArray = new Data[2];
			// Put the Data objects into the Data objects Array
			seriesArray[0] = series1;
			seriesArray[1] = series2;
			// Return Data objects Array to caller
			return seriesArray;
			
	}
}
