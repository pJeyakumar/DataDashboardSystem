package httpTest;

public class ComputationServer {
	
	private AnalysisStrategy currentStrat; 
	private Selection userChoices;
	private String analysisID;
	
	public ComputationServer(Selection input) {
		userChoices = input; 
	}
	
	public void setStrategy(AnalysisStrategy newStrat) {
		currentStrat = newStrat;
	}
	
	public void runStrategy() {
		currentStrat.doAnalysis(userChoices);
	}
	
}
