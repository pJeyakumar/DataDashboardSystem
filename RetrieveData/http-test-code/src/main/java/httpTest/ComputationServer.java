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
	
	// Constructor
	public ComputationServer() {
		
	}
	
	/* DESCRIPTION: Method uses a singleton design pattern to implement only 1 instance of the computational server.        
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */

	public static ComputationServer getInstance() {
		if (instance == null) {
			instance = new ComputationServer();
		}
		return instance;
	}
	
	/* DESCRIPTION: Method sets the selection object of the computational server to the given parameter.
	 * INPUT: Selection
	 * OUTPUT: N/A
	 */
	public void setSelection(Selection choice) {
		userChoices = choice;
	}
	
	/* DESCRIPTION: Method sets the analysis object of the computational server to the given parameter.
	 * INPUT: AnalysisStrategy
	 * OUTPUT: N/A
	 */
	public void setStrategy(AnalysisStrategy newStrat) {
		currentStrat = newStrat;
	}
	
	/* DESCRIPTION: Method runs the specified analysis by passing the Selection object for parameters,
	 *              populating the Results object, and changing the state of the Results object.
	 * INPUT: Results
	 * OUTPUT: N/A
	 */
	public void runStrategy(Results results) {
		System.out.println("RUNNING STRATEGY...");

		currentStrat.doAnalysis(userChoices);

		currentStrat.populateResults(results);

		results.setState(1);
	}
	
}
