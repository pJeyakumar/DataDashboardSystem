package httpTest;
/*
 * NAME:
 * DATE:
 * DESCRIPTION:
 */
public interface IResults 
{
	public void attachViewer (Viewer viewer);
	public boolean detachViewer (ViewerType type) throws Exception;
	public void notifyViewers();
}
