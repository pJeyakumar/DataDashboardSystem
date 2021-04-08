package httpTest;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: Interface describing the methods the classes that will implement it use.
 */
public interface ILogin 
{
	// given the username and password, valid them and provide access to MainDisplay
	public void loginRequest(String inUser, String inPass);
}
