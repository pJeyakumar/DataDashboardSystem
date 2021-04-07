package httpTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
/*
 * NAMES: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-03-31
 * DESCRIPTION: Class that will make an API request to the world bank (based on Selection object choices) and fetch that data and store the two sets into ArrayLists.
 * 				Afterwards, using these ArrayLists, we instantiate an object of the Data class and store them there as firstSet and secondSet. We will then return this
 * 				Data object to the calling class (aka Analysis class)
 */
public class Reader 
{	
	public Reader() {
		
	}
	// Method that will make an API request to World Bank
	public Data retrieveData(Selection choices, String ind)
	{
		String dataName;
		// URL for API request
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json", 
				choices.getCountry(), ind, choices.getStartYr(), choices.getEndYr());
		System.out.println(urlString);
		try 
		{
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) 
			{
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) 
				{
					inline += sc.nextLine();
					
				}
				sc.close();
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
// PIRANA COMMENT -- Create an ArrayList for both value sets (used Double and Integer to cover all cases)
				ArrayList<Double> data1 = new ArrayList<Double>(sizeOfResults);
				ArrayList<Integer> data2 = new ArrayList<Integer>(sizeOfResults);
				dataName = jsonArray.get(1).getAsJsonArray().get(0).getAsJsonObject().get("indicator").getAsJsonObject().get("value").getAsString();
				for (int i = 0; i < sizeOfResults; i++) 
				{
// PIRANA COMMENT -- In order to add a value to an ArrayList, you need to use the arrayName.add(value) method
					data2.add(jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt());
					// if the value is NULL, set it as -1.0 (we will use this as an indicator for if the value for the given)
					// year was null when rendering the viewers
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) 
					{
						data1.add(-1.0);
					}
					else 
					{
						data1.add(jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble());
					}
				}
// PIRANA COMMENT -- We create an instance of the Data object
				Data fetchedData = new Data(data1, data2, dataName);
				return fetchedData;		
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block e.printStackTrace();
		}
// PIRANA COMMENT -- If we reached this point, it means an error has occurred, meaning we would return null as something went wrong
		return null;
	}
}


