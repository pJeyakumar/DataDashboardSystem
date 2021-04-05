package httpTest;

import javax.swing.JPanel;

public class ComputationServer {
	
	private AnalysisStrategy currentStrat; 
	private Selection userChoices;
	
	private static ComputationServer instance = null; 
	
	/*recalculate()
	{
		HashMap map = new HashMap(AnalysisBox.analysisID);
		Selection input = new Selection(countryBox.country, fYearBox.sYear, LYearBox.eYear, AnalysisBox.analysisID);
		ComputationServer cs = new ComputationServer(input);
		AnalysisStrategy obj = AnalysisCreator.create(input.getAnalysis());
		cs.setStrategy(obj);
		cs.runStrategy();
	}*/
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
	
	public void runStrategy(Results results) {
		//Results results = new Results();
		System.out.println("RUNNING STRATEGY...");
		currentStrat.doAnalysis(userChoices);
		currentStrat.populateResults(results);
		results.setState(1);
	}
	
}
