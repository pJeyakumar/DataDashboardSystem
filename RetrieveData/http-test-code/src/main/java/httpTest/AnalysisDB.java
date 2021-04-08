package httpTest;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class AnalysisDB {
    
    
    HashMap<String, String[][]> analysis_dict;
    
    boolean country, start, end, viewer;

    String analysis; 
    
    int numOfViewers;
    
    public AnalysisDB() {
        
    }
    
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
    
    public boolean validCountry(String input) {
        System.out.println("Country Check -- Length: ");
        System.out.println(analysis);
        System.out.println(analysis_dict.get(analysis).length);
        
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
    
    public boolean allValid() {
        System.out.println("...");
        System.out.println(country);
        System.out.println(start);
        System.out.println(end);
        System.out.println(viewer);
        return country && start && end && viewer;
    }

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
