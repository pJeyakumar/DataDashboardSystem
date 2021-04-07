package httpTest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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
	// create a proxy object
	RealLogin proxy = new LoginProxy();
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
	public void setAesthetics() 
	{
		usernameLabel.setBounds(50,150,100,30);
	    passwordLabel.setBounds(50,220,100,30);
	    usernameText.setBounds(150,150,150,30);
	    passwordText.setBounds(150,220,150,30);
	    login.setBounds(50,300,100,30);
        reset.setBounds(200,300,100,30);
	}
	public void addContainer() 
	{
        container.add(usernameLabel);
        container.add(passwordLabel);
        container.add(usernameText);
        container.add(passwordText);
        container.add(login);
        container.add(reset);
	}
	public void addActionEvent() 
	{
		login.addActionListener(this);
		reset.addActionListener(this);
	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent press) 
	{
		// if the reset button was pressed, we clear the text boxes
		if(press.getSource() == reset)
		{
			usernameText.setText("");
			passwordText.setText("");
		}
		// if the login button was pressed
		if(press.getSource() == login) 
		{
			// store the text box values into strings
			String inUser = usernameText.getText();
			String inPass = passwordText.getText();
			// call the loginRequest method with our given strings, this will check if the credentials were valid
			proxy.loginRequest(inUser, inPass);
		}
	}
}
