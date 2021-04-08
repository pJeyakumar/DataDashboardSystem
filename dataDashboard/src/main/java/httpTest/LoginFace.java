package httpTest;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-04
 * DESCRIPTION: Will create the Login Interface and have a Reset button as well, will take in the user input for username and password
 * 				and feed it to proxy object to check for validation
 */
public class LoginFace extends JFrame implements ActionListener
{
	// Variable Declaration 
	Container container = getContentPane();
    JLabel usernameLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField usernameText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JButton login = new JButton("LOGIN");
    JButton reset = new JButton("RESET");
    JButton picture1 = new JButton();
    JButton picture2 = new JButton();
	// create a proxy object, created here so that we only create 1 proxy object per run
	RealLogin proxy = new LoginProxy();
	ImageIcon picture = new ImageIcon("god.jpg");
	
	/* DESCRIPTION: Constructor class that will call all the methods needed to set up the container with all the Login features
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */
	LoginFace()
	{
		// Set layout to null at first
		container.setLayout(null);
		// Set the sizes and locations of the labels, text boxes and buttons
		setAesthetics();
		// add all the labels, text boxes and buttons to the container object
		addContainer();
		// create add Action Listeners for both our buttons
		addActionEvent();
	}
	// Main Method
	public static void main(String[] args) 
	{
		// Creates the frame, sets the title, visibility, size and other properties
        LoginFace frame = new LoginFace();
        frame.setTitle("User Login");
        frame.setVisible(true);
        frame.setBounds(10,10,350,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
	}
	/* DESCRIPTION: method that will set the locations and sizes of all the labels, buttons and textboxes
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */
	public void setAesthetics() 
	{
		// set boundaries for labels, boxes and buttons
		usernameLabel.setBounds(50,150,100,30);
	    passwordLabel.setBounds(50,220,100,30);
	    usernameText.setBounds(150,150,150,30);
	    passwordText.setBounds(150,220,150,30);
	    login.setBounds(50,300,100,30);
        reset.setBounds(200,300,100,30);
        picture1.setBounds(50, 350, 100, 100);
        picture2.setBounds(200,350,100,100);
        // PROOF OF GOD
        picture1.setBorder(new EmptyBorder(0,0,0,0));
        picture1.setOpaque(false);
        picture1.setIcon(picture);
        
        picture2.setBorder(new EmptyBorder(0,0,0,0));
        picture2.setOpaque(false);
        picture2.setIcon(picture);
       
        
	}
	/* DESCRIPTION: method that will add all the labels, buttons and textboxes to the container
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */
	public void addContainer() 
	{
		// add the labels, buttons and boxes to the container
        container.add(usernameLabel);
        container.add(passwordLabel);
        container.add(usernameText);
        container.add(passwordText);
        container.add(login);
        container.add(reset);
        container.add(picture1);
        container.add(picture2);
	}
	/* DESCRIPTION: method that will set listeners to our buttons
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */
	public void addActionEvent() 
	{
		// have the system "listen" to the buttons so that an action will be performed if they are pressed
		login.addActionListener(this);
		reset.addActionListener(this);
	}
	@SuppressWarnings("deprecation")
	/* DESCRIPTION: method that will be run whenever an ActionListener object is pressed, based on the button that was pressed, different code
	 * 				will be run
	 * INPUT: ActionEvent
	 * OUTPUT: N/A
	 */
	public void actionPerformed(ActionEvent press) 
	{
		// if the reset button was pressed, we clear the text boxes
		if(press.getSource() == reset)
		{
			resetText();
		}
		// if the login button was pressed
		if(press.getSource() == login) 
		{
			// store the text box values into strings
			String inUser = usernameText.getText();
			String inPass = passwordText.getText();
			// call the loginRequest method with our given strings, this will check if the credentials were valid
			proxy.loginRequest(inUser, inPass);
			// if the loginRequest FAILED, we will come here and reset the text (if it do NOT fail, it won't come here until after the program is basically done its job)
			resetText();
		}
	}
	/* DESCRIPTION: Method for setting the texts in the TextBoxes to "", basically resetting them
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */
	public void resetText()
	{
		usernameText.setText("");
		passwordText.setText("");
	}
}
