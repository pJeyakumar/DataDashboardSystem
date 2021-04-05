package httpTest;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Results_old {
    
	
	private JPanel mainPanel; 
    private ArrayList<Viewer> viewers;
    private int state;
    
    private ArrayList<String> indicators;    // data indicators
    private ArrayList<Double>[] values;
    private ArrayList<Integer>[] years;
    public Results_old() {

        // strategyID
        indicators = new ArrayList<String>();
        viewers = new ArrayList<Viewer>();
        state = 0;
    }
    
    // get data based on its indicator
    public Data getData(String indicator) throws OutOfBoundsException{
        Data returnedData;
        int index = indicators.indexOf(indicator);
        if (index != -1) {
            returnedData = new Data(values.get(index), years);
        } else {
            throw new OutOfBoundsException("Indicator does not exist");
        }
    }
    
    // set data for either a: new indicator or change for existing
//    public void setData(Data newData, String indicator){
//        int index = indicators.indexOf(indicator);
//        if (index != -1) {
//            values.set(index, newData.getFirst());
//            years = newData.getSecond();
//        } else {
//            values.add(newData.getFirst());
//            indicators.add(indicator);
//        }
//    }
    
    public void setData(ArrayList<Double>[] finalData, ArrayList<Integer>[] yrs, 
    		String[] dataNames, String[] axisNames, String analysisID ){
    	int numSeries = finalData.length;
    	
    	values = finalData; 
    	years = yrs;
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
        for (int i = 0 ; i < )
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
        for (Viewer v : viewers){
            v.display(mainPanel, values, years, units, analysisID);
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
