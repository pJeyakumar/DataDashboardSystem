package httpTest;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2921-04-06
 * DESCRIPTION: Responsible for handling any selections made by the user
 */

public class Selection {
	
	// Private Instance Variables
	private String country;
	private int startYear;
	private int endYear;
	private String analysisType = "";
	private HashMap<String, String> country_dict;
	
	/* DESCRIPTION: Used to load a country dictionary via a hashmap
	 * INPUT: N/A
	 * OUTPUT: N/A
	 */
	public Selection() {
		// Used to load a country dictionary (hashmap)
		String line; 
		String[] fields;
		country_dict = new HashMap<String,String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("country_list.csv"));
			while ((line = br.readLine()) != null) {
				fields = line.split(",");
				country_dict.put(fields[1], fields[5]);								
			}
			
			br.close();        
		}catch(IOException e ) {
			e.printStackTrace();
		}	
	}
	
	/* DESCRIPTION: Method sets the user selection for country.
	 * INPUT: String
	 * OUTPUT: N/A
	 */
	public void setCountry(String inCountry) {
		country = country_dict.get(inCountry);
	}
	
	/* DESCRIPTION: Method sets the user selection for their chosen analysis type.
	 * INPUT: String
	 * OUTPUT: N/A
	 */
	public void setAnalysis(String inAnalysis) {
		analysisType = inAnalysis; 
	}
	
	/* DESCRIPTION: Method sets the user selection for their chosen start year.
	 * INPUT: Integer
	 * OUTPUT: N/A
	 */
	public void setStartYear(int start) {
		startYear = start;
	}
	
	/* DESCRIPTION: Method sets the user selection for their chosen end year.
	 * INPUT: Integer
	 * OUTPUT: N/A
	 */
	public void setEndYear(int end) {
		endYear = end;
	}
	
	/* DESCRIPTION: Method obtains the current user's analysis type selection.
	 * INPUT: N/A
	 * OUTPUT: String
	 */
	public String getAnalysis() {
		return this.analysisType;
	}
	
	/* DESCRIPTION: Method obtains the current user's country selection.
	 * INPUT: N/A
	 * OUTPUT: String
	 */
	public String getCountry() {
		return this.country;
	}
	
	/* DESCRIPTION: Method obtains the current user's start year selection.
	 * INPUT: N/A
	 * OUTPUT: Integer
	 */
	public int getStartYr() {
		return this.startYear;
	}
	
	/* DESCRIPTION: Method obtains the current user's end year selection.
	 * INPUT: N/A
	 * OUTPUT: Integer
	 */
	public int getEndYr() {
		return this.endYear;
	}
	
}
