package httpTest;

/**
 * @author - Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 *
 * Analysis Creator Class:
 *  - Sole purpose is to return a specific type of AnalysisStrategy object
 *  - Example use:
 *    - String analysisType = SelectionObject.getAnalysis();
 *    - AnalysisStrategy AnalysisA = AnalysisCreator.create(analysisType)
 *      - Then we can do stuff like AnalysisA.doAnalysis() etc.
 */

public class AnalysisCreator {
	
	public AnalysisCreator() {}
	
	public static AnalysisStrategy create(String analysisType) {
		
		if (analysisType.equals("A")) {
			return new AnalysisA();
		} else if (analysisType.equals("B")) {
			return new AnalysisB();
		} else if (analysisType.equals("C")) {
			return new AnalysisC();
		} else if (analysisType.equals("D")) {
			return new AnalysisD();
		} else if (analysisType.equals("E")) {
			return new AnalysisE();
		} else if (analysisType.equals("F")) {
			return new AnalysisF();
		} else if (analysisType.equals("G")) {
			return new AnalysisG();
		} else {
			return new AnalysisH();
		}
	}
} // End AnalysisCreator
