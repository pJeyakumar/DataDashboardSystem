package httpTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginProxy extends RealLogin
{
	
	@Override
	public void loginRequest() 
	{
		// Variable Declarations
		String[] usernames;
		String[] passwords;
		Boolean credCheck = false;
		Boolean userIn = true;
		String line;
		String[] fields;
		int indexCounter = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("credentials.txt"));
			while ((line = br.readLine()) != null) {
				fields = line.split(",");
				usernames[indexCounter] = fields[0];
				passwords[indexCounter] = fields[1];
				indexCounter++;
			}
			
			br.close();        
		}
		catch(IOException e )
		{
			e.printStackTrace();	
		}
		
		// Render User name and Password boxes 
		
		String inUser = null;
		String inPass = null; 
		
		while(userIn)
		{
			//Login Button action call
		//{	
			// Load these two SOMEHOW 
			inUser = box1.getVal();
			inPass = box2.getVal(); 
			break;
		//}
		}
		
			if(inUser != null || inPass != null) 
			{
				// Search and verify user name AND password (both should be on same indexes)
				for(int i = 0; i < usernames.length; i++) 
				{
					if(inUser.equals(usernames[i]) && inPass.equals(passwords[i])) 
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
				super.loginRequest();
			}
			else 
			{
				System.out.println("Login Failed.");
			}
		
	}
}
