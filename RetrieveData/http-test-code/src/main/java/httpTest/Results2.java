package httpTest;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Results2 {
    
	
	private JPanel mainPanel; 
    private ArrayList<Viewer> viewers;
    private int state;
    
    
    // ---- DRAFT CLASS ----
    // Had an idea for a way less crowded results class where we simply attach the analysis strategy
    // Might not be correct, just a thought; its far more efficient tho
    private AnalysisStrategy analysis; 
    
    public Results2() {

        viewers = new ArrayList<Viewer>();
        state = 0;
    }
    
    public void attachAnalysis(AnalysisStrategy current){
    	analysis = current;
    }
    
    public void setJPanel(JPanel panel) {
    	mainPanel = panel; 
    }
    
    
    public void attachViewer (Viewer viewer) {
        viewers.add(viewer);
    }
    
    public void detachViewer (Viewer viewer) throws Exception{
        if (viewers.size() == 0) {
        	throw new Exception("No viewers are loaded");
        }
        viewers.remove(viewer);
        
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
    	
        for (Viewer v : viewers){
            v.display(mainPanel, analysis.processedData, 
            		analysis.years, analysis.dataNames, 
            		analysis.axisNames, analysis.analysisID);
        }
    }
    
    
}


// attachViewer
// detachViewer
// notifyViewers
// getState
// setState
// getData
// setData
