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
	
	public AnalysisDB() {
		
	}
	
	public AnalysisDB(String analysisID) {
		// Used to load a  dictionary (hashmap)
		
		String line; 
		String[] fields;
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

	}
	
	public boolean validCountry(String input) {
		System.out.println(analysis_dict.size());
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
		String e = analysis_dict.get(analysis)[1][1];
		if (input <= Integer.valueOf(e)) {
			end = true;
			return true;
		}else {
			end = false;
			return false;
		}
	}
	
	public boolean validViewer(String input) {
		String[] viewers = analysis_dict.get(analysis)[2];
		for (int i = 0 ; i < viewers.length ; i ++) {
			 if (viewers[i].equals(input)) {
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
}
