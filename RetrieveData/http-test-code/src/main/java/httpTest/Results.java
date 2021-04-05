package httpTest;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Results implements IResults
{
   
	private JPanel mainPanel; 
    private ArrayList<Viewer> viewers;
    private int state;
    
    
    // ---- DRAFT CLASS ----
    // Had an idea for a way less crowded results class where we simply attach the analysis strategy
    // Might not be correct, just a thought; its far more efficient tho
    private AnalysisStrategy analysis; 
    
    public Results() {

        viewers = new ArrayList<Viewer>();
        state = 0;
    }
    
    public void attachAnalysis(AnalysisStrategy current){
    	analysis = current;
    }
       
    
    public void attachViewer (Viewer viewer) {
        viewers.add(viewer);
    }
    
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
        throw new Exception("Viewer not found");
    }
    
    
    public int getState() {
        return state;
    }
    
    public void setState(int newState) {
        state = newState;
        notifyViewers();
    }
    
    public void notifyViewers() {
        // take values from result object, display these new values
        // for every viewer, update data --> update()
        // take values from result object, display these new values
    	
    	
    	// Check : at least one viewer loaded 
    	// Check : valid analysis strategy loaded 
    	System.out.println("NOTIFYING VIEWERS ...");
        for (Viewer v : viewers){
            v.display(mainPanel, analysis.getProcessedData(), analysis.getYears(), analysis.getDataNames(), analysis.getAxisNames(), analysis.getAnalysisID());
        }
    }
    public void emptyViewers()
    {
    	System.out.println("EMPTYING VIEWERS ...");
    	this.viewers = new ArrayList<Viewer>();
    }
}


// attachViewer
// detachViewer
// notifyViewers
// getState
// setState
// getData
// setData
