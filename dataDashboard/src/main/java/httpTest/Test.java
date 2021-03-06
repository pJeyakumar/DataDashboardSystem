package httpTest;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		
		AnalysisStrategy myAnalysis = new AnalysisC();
		Selection myS = new Selection();
		myS.setCountry("Spain");
		myS.setStartYear(1985);
		myS.setEndYear(2015);
		Data[] list = myAnalysis.retrieveData(myS);
		ArrayList<Double> current;
		for (int i = 0; i < list.length; i++) {
			current = list[i].getFirst();
			
			for (int j = 0 ; j < current.size(); j++) {
				System.out.printf("%f  ", current.get(j));
			}
			System.out.println("");
		}
		
		System.out.println(". ".repeat(20));
	}
}


// 1990 start
// 2009
// Brazil
// China
