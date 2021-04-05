package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: RealSubject class, this class will be hidden behind a proxy and if accessed, will entail that the credentials were valid
 * 				and now the main user interface will be accessed.
 */
public class RealLogin implements ILogin
{
	public void loginRequest(String inUser, String inPass)
	{
		// go to mainUI
		MainDisplay.main(null);
		System.out.println("Success");
	}
}
