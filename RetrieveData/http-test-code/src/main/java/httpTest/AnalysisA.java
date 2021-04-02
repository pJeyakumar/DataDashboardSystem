package httpTest;
// PIRANAVAN
import java.util.ArrayList;

public class AnalysisA extends AnalysisStrategy{
	
	
	public AnalysisA() {
		
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
		Data[] finalData = retrieveData(selection);
		ArrayList<Double>[] a = new ArrayList[2];
		a[0] = finalData[0].getFirst();
		a[1] = finalData[1].getFirst();
		
		
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
			Data series1 = reader.retrieveData(selection, "EG.ELC.RNEW.ZS");
			Data series2 = reader.retrieveData(selection, "EG.FEC.RNEW.ZS");
			Data[] seriesArray = new Data[2];
			seriesArray[0] = series1;
			seriesArray[1] = series2;
			return seriesArray;
	}
}
