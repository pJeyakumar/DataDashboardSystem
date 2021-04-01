package httpTest;
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

abstract class AnalysisStrategy {
	
	// Reader class which will return Data objects
	private Reader reader;
	
	private Data[] data;
	private Data[] processedData;
	private int numOfSeries;
	
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
	public abstract void doAnalysis(Selection selection);
	
	
	/**
	 * Retrieve Data
	 * To be called from Do Analysis to get the data to be processed
	 * @return Data
	 */
	public abstract Data retrieveData();
	
	/**
	 * Populate Results
	 * @param Data to be Processed
	 */
	public void populateResults(Results res) {
		res.setData(this.processedData);
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
