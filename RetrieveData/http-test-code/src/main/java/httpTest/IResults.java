package httpTest;
/*
 * NAME:
 * DATE:
 * DESCRIPTION:
 */
public interface IResults 
{
	public void attachViewer (Viewer viewer);
	public void detachViewer (Viewer viewer) throws Exception;
	public void notifyViewers();
}
