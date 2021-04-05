package httpTest;

import javax.swing.JPanel;

public class ComputationServer {
	
	private AnalysisStrategy currentStrat; 
	private Selection userChoices;
	
	private static ComputationServer instance = null; 
	
	public ComputationServer() {
		
	}
	
	// Singleton design pattern, not sure if we want to keep this 
	public static ComputationServer getInstance() {
		if (instance == null) {
			instance = new ComputationServer();
		}
		return instance;
	}
	
	public void setSelection(Selection choice) {
		userChoices = choice;
	}
	
	public void setStrategy(AnalysisStrategy newStrat) {
		currentStrat = newStrat;
	}
	
	public void runStrategy(JPanel panel, Results results) {
		//Results results = new Results();
		currentStrat.doAnalysis(userChoices);
		results.setJPanel(panel);
		currentStrat.populateResults(results);
	}
	
}
