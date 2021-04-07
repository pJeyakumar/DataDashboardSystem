package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-02
 * DESCRIPTION: Results interface containing the methods the Results class must implement
 */
public interface IResults 
{
	// add the Viewer to the Viewer List
	public void attachViewer (Viewer viewer);
	// remove the Viewer from the Viewer List, provided that it exists
	public boolean detachViewer (ViewerType type) throws Exception;
	// go through each Viewer in the List and call their display methods
	public void notifyViewers();
}
