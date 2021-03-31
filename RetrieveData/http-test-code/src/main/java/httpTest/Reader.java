package httpTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
/*
 * NAMES: Allan Zhang, John Palmer, Piranavan Jeyakumar, Shoumik Shill
 * DATE: 2021-03-30
 * DESCRIPTION: 
 */
public class Reader 
{
// PIRANA COMMENT -- The issue i'm having: so far the code has no syntax errors for when I explicitly declare what the generic types are,
//					 in this case, both generic types are Integers. Im not sure how I can replace <Integer,Integer> with <A,B> so that we
//					 can choose to put any type there. We need to first figure out how we're going to find out what types the data arrays are
// 					 and then find out how to make it so that lines 18,42,43 and 58 the <Integer, Integer> can be changed to <A,B> where we can 
//					 decide what A and B will be depending on the data array. For example, if we want A = "String" and B = "Integer".
	// Method that will make an API request to World Bank
	
	public Reader() {
		
	}
	
	public Data<Integer,Integer> retrieveData(Selection choices)
	{
		
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json", 
				choices.getCountry(), choices.getAnalysis(), choices.getStartYr(), choices.getEndYr());
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
// PIRANA COMMENT -- Create an ArrayList of Generic Type (I put Integer here)
				ArrayList<Integer> data1 = new ArrayList<Integer>(sizeOfResults);
				ArrayList<Integer> data2 = new ArrayList<Integer>(sizeOfResults);
				for (int i = 0; i < sizeOfResults; i++) 
				{
// PIRANA COMMENT -- In order to add a value to an ArrayList, you need to use the arrayName.add(value) method
					data1.add(jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt());
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) 
					{
						data2.add(0);
					}
					else 
					{
						data2.add(jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsInt());
					}
				}
// PIRANA COMMENT -- We create an instance of the Data object with the generic parameters being Integer and Integer
				Data<Integer,Integer> fetchedData = new Data<Integer,Integer>(data1, data2);
				return fetchedData;
				
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block e.printStackTrace();
		}
// PIRANA COMMENT -- If we reached this point, it means an error has occured, meaning we would return null as something went wrong
		return null;
	}
}


