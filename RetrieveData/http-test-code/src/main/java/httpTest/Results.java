package httpTest;

import java.util.ArrayList;
import java.util.List;

public class Results {
    
    private ArrayList<Viewer> viewers;
    private int state;
    
    private ArrayList<String> indicators;    // data indicators
    private ArrayList<Double>[] values;
    private ArrayList<Integer> years;
    public Results() {
        values = new ArrayList[3]; // depends on series of data
        years = new ArrayList<Integer>();
        indicators = new ArrayList<String>();
        
        viewers = new ArrayList<Viewer>();
        state = 0;
        // strategyID
        
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
    public void setData(Data newData, String indicator){
        int index = indicators.indexOf(indicator);
        if (index != -1) {
            values.set(index, newData.getFirst());
            years = newData.getSecond();
        } else {
            values.add(newData.getFirst());
            indicators.add(indicator);
        }
    }
    
    public void attachViewer (Viewer viewer) {
        viewers.add(viewer);
    }
    
    public void detachViewer (Viewer viewer) throws Exception{
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
        for v in viewers {
            v.display(JPanel west);
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
