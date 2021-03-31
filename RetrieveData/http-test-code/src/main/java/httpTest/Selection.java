package httpTest;

public class Selection {
	
	private String country;
	private int startYear;
	private int endYear;
	private String analysisType; 
	
	public Selection(String analysis, String ctry, int start, int end) {
		country = ctry;
		startYear = start;
		endYear = end;
		analysisType = analysis; 
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
