package httpTest;

import javax.swing.JPanel;
/**
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
	
	/** DESCRIPTION: Constructor for a computation server object  
	 */
	public ComputationServer() {
		
	}
	
	/** DESCRIPTION: Method uses a singleton design pattern to implement only 1 instance of the computational server.        
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */
	public static ComputationServer getInstance() {
		if (instance == null) {
			instance = new ComputationServer();
		}
		return instance;
	}
	
	/** DESCRIPTION: Method sets the selection object of the computational server to the given parameter.
	 * @param choice A selection object to be used as the current user input
	 * OUTPUT: N/A
	 */
	public void setSelection(Selection choice) {
		userChoices = choice;
	}
	
	/** DESCRIPTION: Method sets the analysis object of the computational server to the given parameter.
	 * @param AnalysisStrategy String ID of the analysis to be created and used
	 * OUTPUT: N/A
	 */
	public void setStrategy(String analysisID) {
		AnalysisStrategy analysis = AnalysisCreator.create(analysisID);
		currentStrat = analysis;
	}
	
	/** DESCRIPTION: Method runs the specified analysis by passing the Selection object for parameters,
	 *              populating the Results object, and changing the state of the Results object.
	 * @param results The results object to be used for the analysis 
	 * OUTPUT: N/A
	 */
	public void runStrategy(Results results) {
		System.out.println("RUNNING STRATEGY...");

		currentStrat.doAnalysis(userChoices);

		currentStrat.populateResults(results);

		results.setState(1);
	}
	
}
