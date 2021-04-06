package httpTest;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Selection {
	
	private String country;
	private int startYear;
	private int endYear;
	private String analysisType = "";
	private HashMap<String, String> country_dict;
	
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
	
	public void setCountry(String inCountry) {
		country = country_dict.get(inCountry);
	}
	
	public void setAnalysis(String inAnalysis) {
		analysisType = inAnalysis; 
	}
	
	public void setStartYear(int start) {
		startYear = start;
	}
	
	public void setEndYear(int end) {
		endYear = end;
	}
	
	public String getAnalysis() {
		return this.analysisType;
	}
	
	public String getCountry() {
		return this.country;
	}
	public int getStartYr() {
		return this.startYear;
	}
	public int getEndYr() {
		return this.endYear;
	}
	
}
