package httpTest;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		Selection myChoices = new Selection("SP.POP.TOTL", "can", 2000, 2005);
		//System.out.printf("%d", myChoices.getStartYr());
		
		Reader myReader = new Reader();
		
		Data result = myReader.retrieveData(myChoices);
		
		ArrayList<Double> first = result.getFirst();
		ArrayList<Integer> second = result.getSecond();
		
		for (int i = 0; i < first.size(); i++) {
			System.out.printf("%f \n", first.get(i));
		}
		
	}
}
