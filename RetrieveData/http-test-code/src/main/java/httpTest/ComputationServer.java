package httpTest;

import javax.swing.JPanel;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-03
 * DESCRIPTION: Computation server class, given the Selection Object and Analysis Object, the recalculate button will call the run strategy method
 * 				and using the set AnalysisStrategy object and Selection object this class will call the analysis and enable it to doAnalysis and populate
 * 				the results object
 */
public class ComputationServer {
	
	private AnalysisStrategy currentStrat; 
	private Selection userChoices;
	
	private static ComputationServer instance = null; 
	
	public ComputationServer() {
		
	}
	
	// Singleton design pattern to implement only 1 instance of the Comp Server
	public static ComputationServer getInstance() {
		if (instance == null) {
			instance = new ComputationServer();
		}
		return instance;
	}
	
	// Set the selection object of the Comp Server to given parameter
	public void setSelection(Selection choice) {
		userChoices = choice;
	}
	
	// Set the analysis object of the Comp Server to given parameter
	public void setStrategy(AnalysisStrategy newStrat) {
		currentStrat = newStrat;
	}
	
	public void runStrategy(Results results) {
		System.out.println("RUNNING STRATEGY...");
		// Run the Specified Analysis and pass the Selection object for parameters
		currentStrat.doAnalysis(userChoices);
		// Populate the results object
		currentStrat.populateResults(results);
		// Set the state of the results object to 1 (indicating change)
		results.setState(1);
	}
	
}
