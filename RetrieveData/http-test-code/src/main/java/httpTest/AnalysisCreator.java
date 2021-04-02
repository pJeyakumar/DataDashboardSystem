package httpTest;

public class AnalysisCreator {
	
	
	public AnalysisCreator() {
		
	}
	
	public AnalysisStrategy create(String analysisType) {
		
		switch(analysisType) {
			case "":
				return new AnalysisA();
			case "":
				return new AnalysisB();
			case "":
				return new AnalysisC();
			case "":
				return new AnalysisD();
			case "":
				return new AnalysisE();
			case "":
				return new AnalysisF();
			case "":
				return new AnalysisG();
			case "":
				return new AnalysisH();
		}
	}
}
