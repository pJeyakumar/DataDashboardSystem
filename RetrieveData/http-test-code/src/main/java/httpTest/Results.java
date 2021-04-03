package httpTest;

import java.util.ArrayList;
import java.util.List;

public class Results {
	
	private List<Viewer> viewers;
	private int state;
	
	private String type;
	private ArrayList<Double>[] values;
	private ArrayList<Integer> years;
	private String[] units; 
	
	public Results(Data data) {
		this.values = data.getFirst();
		this.years = data.getSecond();
		this.type = data.getTypeA();
		
		this.viewers = new ArrayList<Viewer>();
		this.state = 0;
		
	}
	
	
	public void setData(ArrayList<Double>[] processedData, ArrayList<Integer>[] years, String[] units){
		// Load 
		//private ArrayList<Double>[] values;
		//private ArrayList<Integer> years;
		// 
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
	
	public void setState(int state) {
		this.state = state;
		notifyViewers();
	}
	
	public void notifyViewers() {
		// take values from result object, display these new values
		// for every viewer, update data --> update()
		// take values from result object, display these new values
		for v in (ATTACHED VIEWERS) {
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