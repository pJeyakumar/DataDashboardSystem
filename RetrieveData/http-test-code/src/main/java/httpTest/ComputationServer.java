package httpTest;

public class ComputationServer {
	
	private AnalysisStrategy currentStrat; 
	private Selection userChoices;
	//private String analysisID;
	/*recalculate()
	{
		HashMap map = new HashMap(AnalysisBox.analysisID);
		Selection input = new Selection(countryBox.country, fYearBox.sYear, LYearBox.eYear, AnalysisBox.analysisID);
		ComputationServer cs = new ComputationServer(input);
		AnalysisStrategy obj = AnalysisCreator.create(input.getAnalysis());
		cs.setStrategy(obj);
		cs.runStrategy();
	}*/
	public ComputationServer(Selection input) {
		userChoices = input;
	}
	
	public void setStrategy(AnalysisStrategy newStrat) {
		currentStrat = newStrat;
	}
	
	public void runStrategy() {
		currentStrat.doAnalysis(userChoices /*,results object*/);
	}
	
}
