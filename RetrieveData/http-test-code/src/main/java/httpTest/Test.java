package httpTest;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		Selection myChoices = new Selection("SP.POP.TOTL", "can", 2000, 2001);
		System.out.printf("%d", myChoices.getStartYr());
		
		Reader myReader = new Reader();
		
		DataPair<Integer, Integer> result = myReader.retrieveData(myChoices);
		
		ArrayList<Integer> val = result.getFirst();
		
		for (int i = 0; i < val.size(); i++) {
			System.out.printf("%d \n", val.get(i));
		}
		
	}
}
