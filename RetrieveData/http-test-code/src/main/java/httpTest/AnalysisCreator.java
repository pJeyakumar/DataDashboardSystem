package httpTest;

public class AnalysisCreator {
	
	
	public AnalysisCreator() {
		
	}
	
	public AnalysisStrategy create(String analysisType) {
		
		switch(analysisType) {
			case "":
				return new AnalysisA();
		}
	}
}
