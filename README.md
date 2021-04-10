# Data Dashboard System 

The Data Dashboard system is an interactive system allowing the user to select varying types of analysis' on ENVIRONMENTAL data retrieved from the World Bank Database and display these analysis' through Viewers (e.g Bar Graphs, Line Graphs, etc) that the user may select.

**LOGIN:**
Our system features a Login panel, in which the user must enter a username and password, that will be verified by our system, in order to gain access to **MAIN_DISPLAY**.
![Login_Interface](https://user-images.githubusercontent.com/57971751/114282549-8c5e2100-9a12-11eb-9900-1ec4573eb704.png)

**MAIN_DISPLAY:**
Our Main_Display will have drop-down menus, for which the user will select:
1. The analysis they'd like to run.
2. The country for which the data for the analysis will be based on.
3. The start and end year periods for the data.
4. The Viewers the user would like to be displayed.

![Main_Display_Empty](https://user-images.githubusercontent.com/57971751/114282561-a39d0e80-9a12-11eb-8cac-233aa62873f7.png)
![Main_Display_Populated](https://user-images.githubusercontent.com/57971751/114282587-badbfc00-9a12-11eb-86ad-767f562222d0.png)

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

**PROJECT CREATED BY:**
- _Allan Zhang_
- _John Palmer_
- _Piranavan Jeyakumar_
- _Shoumik Shill_

