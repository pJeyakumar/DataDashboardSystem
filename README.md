# Data Dashboard System 

The Data Dashboard system is an interactive system allowing the user to select varying types of analysis' on ENVIRONMENTAL data retrieved from the World Bank Database and display these analysis' through Viewers (e.g Bar Graphs, Line Graphs, etc) that the user may select.

**LOGIN:**
Our system features a Login panel, in which the user must enter a username and password, that will be verified by our system, in order to gain access to **MAIN_DISPLAY**.
\newline
![Login Interface](https://ibb.co/VjTfCWs)
\newline
**MAIN_DISPLAY:**
Our Main_Display will have drop-down menus, for which the user will select:
1. The analysis they'd like to run.
2. The country for which the data for the analysis will be based on.
3. The start and end year periods for the data.
4. The Viewers the user would like to be displayed.
\newline
![Main Interface (Empty)](https://ibb.co/GsFWDhR)
\newline
![Main Interface (Populated)](https://ibb.co/PD6WgSf)

After the user has made all their selection choices, pressing the recalculate button will:
- fetch the user-specified data from the World Bank using REST API
- perform the analysis and any needed computations on the data
- populate the Results object (the object which will contain the data and notify the Viewers to display the data) with the processed data
- render the data onto the panels on **MAIN_DISPLAY**


To run the program, please run the LoginFace.java class. 
	The Main method there will allow you to interact with the Login Interface.
    After inputting the correct credentials, you will be taken to the Main Interface.
    
USERNAME: user

PASSWORD: pass

