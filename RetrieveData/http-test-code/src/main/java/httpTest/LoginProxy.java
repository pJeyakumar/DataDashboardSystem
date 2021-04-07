package httpTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: Proxy class that will hide the RealSubject class, based on the text file containing the credential this class will check
 * 				if the user entered strings match any of the usernames and passwords stored here. If so it will grant access to the RealSubject
 * 				if not, the system will notify the user of the incorrect credentials
 */
public class LoginProxy extends RealLogin
{
	// instance variable
	private int attemptsRemaining = 3;
	@Override
	/*
	 * DESCRIPTION: method that when given the username and password, will check if they are valid compared to the pre-set credentials text file
	 * 				if valid, this method will call upon the RealLogin loginrequest method to grant access to MainDisplay
	 * 				if invalid, this method will notify the user and display the number of attempts left, if the user surpasses num of attempts, program will terminate
	 * INPUT: String, String
	 * OUTPUT: N/A
	 */
	public void loginRequest(String inUser, String inPass) 
	{
		// Variable Declarations
		ArrayList<String> usernames = new ArrayList<String>();
		ArrayList<String> passwords = new ArrayList<String>();
		Boolean credCheck = false;
		String line;
		String[] fields;
		
		// Read through the credentials file and store the allowed set of user names and passwords into arrays
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader("credentials.txt"));
			while ((line = br.readLine()) != null) {
				// each line contains a [username,password]
				fields = line.split(",");
				usernames.add(fields[0]);
				passwords.add(fields[1]);
			}
			br.close();        
		}
		catch(IOException e )
		{
			e.printStackTrace();	
		}
		
		if(inUser.length() != 0 && inPass.length() != 0) 
		{
			// Search and verify user name AND password (both should be on same indexes)
			for(int i = 0; i < usernames.size(); i++) 
			{
				if(inUser.equals(usernames.get(i)) && inPass.equals(passwords.get(i))) 
				{
					credCheck = true;
					break;
				}
			}
		
		}
		if(credCheck) 
		{
				// Access granted 
				// Call Real Login class (which will call the mainUI)
				super.loginRequest(inUser, inPass);
		}
		else 
		{
			// check if we have surpassed number of attempts
			if(this.attemptsRemaining == 0) 
			{
				System.out.println("Too many failed attempts. Exiting system...");
				System.exit(attemptsRemaining);
			}
			else
			{
				// if not, notify user and decrement number of attempts left
				System.out.println("Incorrect Username or Password. Please try again.");
				System.out.println("Attempts remaining: " + attemptsRemaining);
				this.attemptsRemaining--;
			}
		}
	}
}
