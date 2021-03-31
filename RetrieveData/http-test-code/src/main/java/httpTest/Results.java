package httpTest;

public class Results {
	
	private Data[] storedData; 
	private int numOfSeries; 
	public Results(Data[] inputData) {
		
		storedData = inputData;
		numOfSeries = inputData.length;
	}
	
	public Data getData(int i) throws OutOfBoundsException{
		if (i > numOfSeries) {
			 throw new OutOfBoundsException("Data entry not found");
		}
		
		return storedData[i];
	}
	
}
