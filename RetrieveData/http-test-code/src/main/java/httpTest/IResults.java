package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-02
 * DESCRIPTION: Results interface containing the methods the Results class must implement
 */
public interface IResults 
{
	public void attachViewer (Viewer viewer);
	public boolean detachViewer (ViewerType type) throws Exception;
	public void notifyViewers();
}
