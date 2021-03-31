
/**
 * @author Group 46
 * 
 * Analysis Strategy Class
 * Functional Class that:
 *   1. (Input) Gets fetched Data (calls a Reader Class that returns Data Object)
 *   2. Handles the analysis and computation (if needed for an Analysis) on these Data Objects
 *   3. (Output) Populates this Processed Data to a Results Object
 * Called by
 *  - Computational Server class
 * 
 */

public class AnalysisStrategy {
	
	// Reader class which will return Data objects
	private Reader reader;
	
	private Data[] dataArray;
	private Data[] processedDataArray;
	
	// Constructor - to be created by Computational Server class
	public AnalysisStrategy() {
		this.reader = new Reader();
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
	public void doAnalysis(Selection selection) {
		
		
		
	}
	
	/**
	 * Retrieve Data
	 * To be called from Do Analysis to get the data to be processed
	 * @return Data
	 */
	public Data retrieveData() {
		
		
		// return this.reader.getData();
	}
	
	/**
	 * Populate Results
	 * @param Data to be Processed
	 */
	public populateResults() {
		
		this.processedData
		
	}
	
	/**
	 * package httpTest;

	abstract class Analysis {
	
	public Data[] dataArray; 
	public int numOfSeries; 

	// Main Method 
	public abstract void doAnalysis(Selection userChoice);
	
	public abstract Data retrieveData(Selection userChoice);
	
	public Results populateResults(Data[] inputData) {
		
	}
}	
	 */

} // END CLASS
