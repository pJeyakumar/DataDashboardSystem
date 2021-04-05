package httpTest;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		
		AnalysisDB myDB = new AnalysisDB("Ratio of Electricity production from coal sources vs Renewable electricity output");
		
		System.out.println(myDB.validViewer("Bar Chart"));
	}
}
