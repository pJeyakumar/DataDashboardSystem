package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: RealSubject class, this class will be hidden behind a proxy and if accessed, will entail that the credentials were valid
 * 				and now the main user interface will be accessed.
 */
public class RealLogin implements ILogin
{
	/* DESCRIPTION: Method will only be called if the user's credentials have been verified and are correct. Then this method will simply
	 * 				call the MainDisplay so that the user can access that interface
	 * INPUT: String, String
	 * OUTPUT: N/A
	 */
	public void loginRequest(String inUser, String inPass)
	{
		// go to mainUI
		MainDisplay.main(null);
	}
}
