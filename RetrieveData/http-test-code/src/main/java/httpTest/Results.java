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
    
    /* DESCRIPTION: Constructor for the Results object, includes setting up the list of viewers & the state.
     * INPUT: N/A
     * OUTPUT: N/A
     */
    public Results() {
        viewers = new ArrayList<Viewer>();
        state = 0;
    }
    
    /* DESCRIPTION: Method sets current AnalysisStrategy object to work with.
     * INPUT: AnalysisStrategy
     * OUTPUT: N/A
     */
    public void attachAnalysis(AnalysisStrategy current){
    	analysis = current;
    }
       
    /* DESCRIPTION: Method attaches viewer to the list of viewers to be notified.
     * INPUT: Viewer
     * OUTPUT: N/A
     */
    public void attachViewer (Viewer viewer) {
        viewers.add(viewer);
    }
    
    /* DESCRIPTION: Method removes viewer from the list of viewers to be notified, should it exist.
     * INPUT: ViewerType (to identify the specific viewer)
     * OUTPUT: Boolean (success/fail)
     */
    public boolean detachViewer (ViewerType type) throws Exception{
        if (viewers.size() == 0) {
        	throw new Exception("No viewers are loaded");
        }
        
        for (int i = 0 ; i < viewers.size(); i ++) {
        	if (viewers.get(i).getType() == type) {
        		viewers.remove(i);
        		return true;
        	}
        }
       return false;
    }
    
    /* DESCRIPTION: Return state of the Results object.
     * INPUT: N/A
     * OUTPUT: Integer
     */
    public int getState() {
        return state;
    }
    
    /* DESCRIPTION: Set the state of the Results object.
     * INPUT: Integer
     * OUTPUT: N/A
     */
    public void setState(int newState) {
        state = newState;
        notifyViewers();
    }
    
    /* DESCRIPTION: Method will notify viewers when the state has changed, 
     *              causing the viewers to update their displays with the new data.
     * INPUT: N/A
     * OUTPUT: N/A
     */
    public void notifyViewers() {
    	System.out.println("NOTIFYING VIEWERS ...");
        for (Viewer v : viewers){
            v.display(analysis.getProcessedData(), analysis.getYears(), analysis.getDataNames(), analysis.getAxisNames(), analysis.getAnalysisID());
        }
    }
    
    /* DESCRIPTION: Method empties the list of viewers.
     * INPUT: N/A
     * OUTPUT: N/A
     */
    public void emptyViewers()
    {
    	System.out.println("EMPTYING VIEWERS ...");
    	this.viewers = new ArrayList<Viewer>();
    }
}
