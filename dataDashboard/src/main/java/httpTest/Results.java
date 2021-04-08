package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2921-04-06
 * DESCRIPTION: Results class, ultimately responsible for rendering the results on the selected viewers after the data has been fetched & results have been calculated
 */

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Results implements IResults
{
   
	private JPanel mainPanel; 
    private ArrayList<Viewer> viewers;
    private int state;
    
    private AnalysisStrategy analysis; 
    
    /**
     * DESCRIPTION: Constructor for the Results object, includes setting up the list of viewers & the state.
     */
    public Results() {
        viewers = new ArrayList<Viewer>();
        state = 0;
    }
    
    /** DESCRIPTION: Method sets current AnalysisStrategy object to work with.
     * @param AnalysisStrategy The analysis strategy to be attached to the current result object 
     */
    public void attachAnalysis(AnalysisStrategy current){
    	analysis = current;
    }
       
    /** DESCRIPTION: Method attaches viewer to the list of viewers to be notified.
     * @param viewer The viewer object to be attached 
     */
    public void attachViewer (Viewer viewer) {
        viewers.add(viewer);
    }
    
    /** 
     * DESCRIPTION: Method removes viewer from the list of viewers to be notified, should it exist.
     * @type ViewerType Type of viewer to be removed
     * @throws ViewerNotFoundException 
     * @return Boolean indicating whether viewer has been detached
     */
    public boolean detachViewer (ViewerType type) throws ViewerNotFoundException{
        // Throw exception if no viewers
    	if (viewers.size() == 0) {
        	throw new ViewerNotFoundException("No viewers are loaded");
        }
        
        // Search through list of viewers are remove the correct one 
        for (int i = 0 ; i < viewers.size(); i ++) {
        	if (viewers.get(i).getType() == type) {
        		viewers.remove(i);
        		return true;
        	}
        }
       return false;
    }
    
    /** 
     * DESCRIPTION: Return state of the Results object.
     * @return The current state of the results object
     */
    public int getState() {
        return state;
    }
    
    /** DESCRIPTION: Set the state of the Results object.
     * @param newState The new state of the results object
     */
    public void setState(int newState) {
        state = newState;
        notifyViewers();
    }
    
    /** DESCRIPTION: Method will notify viewers when the state has changed, 
     *              causing the viewers to update their displays with the new data.
     */
    public void notifyViewers() {
    	System.out.println("NOTIFYING VIEWERS ...");
        for (Viewer v : viewers){
            v.display(analysis.getProcessedData(), analysis.getYears(), analysis.getDataNames(), analysis.getAxisNames(), analysis.getAnalysisID());
        }
    }
    
    /** DESCRIPTION: Method empties the list of viewers.
     */
    public void emptyViewers()
    {
    	System.out.println("EMPTYING VIEWERS ...");
    	this.viewers = new ArrayList<Viewer>();
    }
}
