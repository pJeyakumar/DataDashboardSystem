package httpTest;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * NAME: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-04-07
 * DESCRIPTION: Analysis database class used to check whether user selections of country, start year, end year, and viewers are valid 
 * for the chosen type of analysis. It references the analysis_check.txt file to function
 */
public class AnalysisDB {
    
    
    HashMap<String, String[][]> analysis_dict; 
    boolean country, start, end, viewer;
    String analysis; 
    int numOfViewers;
    
	/** DESCRIPTION: Constructor for the analysis DB class
	 * @param analysisID The string name of the analysis type to create
	 */
    public AnalysisDB(String analysisID) {
        // Used to load a  dictionary (hashmap)
        
        String line; 
        String[] fields;
        
        // Load analysis_dict (hash map) from the analysis_check.txt file 
        analysis_dict = new HashMap<String,String[][]>();
        analysis = analysisID;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("analysis_check.txt"));
            while ((line = br.readLine()) != null) {

                fields = line.split(":");
                String[][] list = new String[3][];
                
                String[] items;
                System.out.println(list.length);
                System.out.println(fields.length);
                for (int i = 1 ; i <= 3 ; i ++) {
                    list[i-1] = fields[i].split(",");
                     
                }
                
                analysis_dict.put(fields[0], list);                             
            }
            
            br.close();        
        }catch(IOException e ) {
            e.printStackTrace();
        }
        
        country = false;
        start = false;
        end = false;
        viewer = false;
        numOfViewers = 0;

    }
    
	/** DESCRIPTION: Method to check whether the currently selected country is valid 
	 * @param input String describing the country to check 
	 * @return Boolean indicating whether country is valid 
	 */
    public boolean validCountry(String input) {        
        // CHECK FIRST STRING ARRAY (COUNTRIES)
        String [] countries = analysis_dict.get(analysis)[0];
        
        if (countries.length != 0) {
            for (int i = 0 ; i < countries.length ; i ++) {
                 if (countries[i].equals(input)) {
                     country = false;
                     return false;
                 }
            }
        }

        country = true;
        return true;
    }
    
	/** DESCRIPTION: Method to check whether the currently selected start year is valid 
	 * @param input Integer of the start year to check
	 * @return Boolean indicating whether start year is valid 
	 */
    public boolean validStartYr(int input) {
        
        // CHECK FIRST STRING ARRAY (COUNTRIES)
        String s = analysis_dict.get(analysis)[1][0];
        if (input >= Integer.valueOf(s)) {
            start = true;
            return true;
        }else {
            start = false;
            return false;
        }
        
    }
    
	/** DESCRIPTION: Method to check whether the currently selected end year is valid 
	 * @param input Integer of the end year to check
	 * @return Boolean indicating whether end year is valid 
	 */
    public boolean validEndYr(int input) {
        // CHECK SECOND STRING ARRAY (YEARS)
        String e = analysis_dict.get(analysis)[1][1];
        if (input <= Integer.valueOf(e)) {
            end = true;
            return true;
        }else {
            end = false;
            return false;
        }
    }
    
	/** DESCRIPTION: Method to check whether the currently selected viewer is valid 
	 * @param input String describing the viewer to check 
	 * @return Boolean indicating whether viewer is valid 
	 */
    public boolean validViewer(String input)
    {
        // CHECK THIRD STRING ARRAY (VIEWERS)
        String[] viewers = analysis_dict.get(analysis)[2];
        for (int i = 0 ; i < viewers.length ; i ++) 
        {
             if (viewers[i].equals(input)) 
             {
                 return false;
             }
        }
        viewer = true;
        return true;
        
    }
    
	/** DESCRIPTION: Method to check whether all selections are valid 
	 * @return Boolean indicating whether all choices are valid 
	 */
    public boolean allValid() {
        return country && start && end && viewer;
    }
    
	/** DESCRIPTION: Method to get all boolean flags 
	 * @return Array of booleans indicating whether user selections are valid
	 */
    public Boolean[] getTruth() {
        // create a Boolean Array
        Boolean[] flags = new Boolean[4];
        // set each index to the values of our instance variables respectively
        flags[0] = country;
        flags[1] = start;
        flags[2] = end;
        flags[3] = viewer;
        return flags;
    }
}
