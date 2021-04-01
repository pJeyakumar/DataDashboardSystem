package httpTest;

import java.io.IOException;

public class LoginProxy extends Login{
	private MainSystem main;
	private boolean userCheck;
	private boolean passCheck;
	
	
	private String[] usernames;
	private String[] passwords; 
	
	public void loginRequest() {
		

		try {
			// Open ASCII Credentials file 
			// Scanner/Reader to read through 
			
			// Load usernames and passwords into memory for easy access
		}catch(IOException error) {
			error.printStackTrace();
		}
		
		// Render Username and Password boxes 
		
		String inUser;
		String inPass; 
		
		while(userIn)
			// Load these two SOMEHOW 
			inUser = box1;
			inPass = box2; 
			
			// Search and verify username [FOR LOOP]  if (inUser == usernames[i]) userCheck = True;
			
			// Search and verify password [FOR LOOP]  if (inPass == passwords[i]) passCheck = True;
			
	
			if(passCheck && userCheck) {
				// Access granted 
				// Call main system 
				main();
			}
		
	}
}
