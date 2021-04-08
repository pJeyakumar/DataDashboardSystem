package httpTest;

/**
 * @author - Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * @date - April 7, 2021
 *
 * Analysis Creator Class:
 *  - Sole purpose is to return a specific type of AnalysisStrategy object
 *  - Example use:
 *    - String analysisType = SelectionObject.getAnalysis();
 *    - AnalysisStrategy AnalysisA = AnalysisCreator.create(analysisType)
 *      - Then we can do stuff like AnalysisA.doAnalysis() etc.
 */

public class AnalysisCreator {
	
	// Constructor just to instantiate
	public AnalysisCreator() {}
	
	/**
	 * Return a new specific analysis strategy object
	 * @param analysis type
	 * @return analysis strategy object based on that type
	 */
	public static AnalysisStrategy create(String analysisType) {
		
		if (analysisType.equals("Renewable electricity output vs Renewable energy consumption")) {
			return new AnalysisA();
		} else if (analysisType.equals("Ratio of Electricity production from coal sources vs Renewable electricity output")) {
			return new AnalysisB();
		} else if (analysisType.equals("Forest area (% of land area)")) {
			return new AnalysisC();
		} else if (analysisType.equals("Forest area (% of land area) vs Average GHG net emissions/removals by LUCF")) {
			return new AnalysisD();
		} else if (analysisType.equals("Agricultural Land vs NO2 Emissions vs Methane Emissions")) {
			return new AnalysisE();
		} else if (analysisType.equals("Total GHG Emissions vs % Urban Population vs % Fossil Fuel Energy Consumption")) {
			return new AnalysisF();
		} else if (analysisType.equals("Ratio of agricultural land (% of total area) vs forest land (%)")) {
			return new AnalysisG();
		} else {
			return new AnalysisH(); // "Average Agricultural land (% of land area)"
		}
	}
} // End AnalysisCreator
