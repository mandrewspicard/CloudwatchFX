package MGB.CloudwatchFX;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.json.JSONObject;

import java.sql.ResultSet;


// This is our backend file, it handles all the SQL and API work
public class Client
{
	
	// Returns a JSon file with daily data for the city
	public JSONObject dailyQuery(String cityCode) throws Exception
	{
		Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/CloudData.db");
		Statement stmt = conn.createStatement();
		
		// Checks if the desired data is already available on the SQL server
		ResultSet rs = stmt.executeQuery("select data from dailyData where cityCode = '" + cityCode + "' limit 1;");
		if (rs.next() == true)
		{
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 stmt.close();
			 conn.close();
			 
			 return outputJson.getObject();
		}
		
		else
		{
			// Requests the data from meteosource if it's not already in the SQL database
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/daily?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "a15a997526mshbec312fcc154cf1p1ce875jsn9d47aa83f2c4")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			

			stmt.execute("INSERT INTO dailyData(cityCode, data) VALUES ('" + cityCode +"', '" + response.getBody() + "');");
			stmt.close();
			conn.close();
			
			
			// JSON object is returned
			return(response.getBody().getObject());
		}
	}
	
	// Returns a JSon file with hourly data for the city
	public JSONObject hourlyQuery(String cityCode) throws Exception
	{	
		Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/CloudData.db");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select data from hourlyData where cityCode = '" + cityCode + "' limit 1;");
		if (rs.next() == true)
		{
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 stmt.close();
			 conn.close();
			 
			 return outputJson.getObject();
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/hourly?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "a15a997526mshbec312fcc154cf1p1ce875jsn9d47aa83f2c4")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			
			// Same idea, just with a different schema
			stmt.execute("INSERT INTO hourlyData(cityCode, data) VALUES ('" + cityCode +"', '" + response.getBody() + "');");
			stmt.close();
			conn.close();
			
			return(response.getBody().getObject());
		}
	}
	
	// Returns a JSon file with alert data for the city
	public JSONObject alertsQuery(String cityCode) throws Exception
	{	

		Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/CloudData.db");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select data from alerts where cityCode = '" + cityCode + "' limit 1;");
		if (rs.next() == true)
		{
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 
			 stmt.close();
			 conn.close();
			 return outputJson.getObject();
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/alerts?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "a15a997526mshbec312fcc154cf1p1ce875jsn9d47aa83f2c4")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			

			stmt.execute("INSERT INTO alerts(cityCode, data) VALUES ('" + cityCode +"', '" + response.getBody() + "');");
			
			stmt.close();
			conn.close();
			return(response.getBody().getObject());
		}
	}
	
	
	// Finds the city code for a city written in normal English
	public String cityCodeLookup(String cityInput) throws Exception
	{
		String cityCode = "";
		Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/CloudData.db");
		Statement stmt = conn.createStatement();
		
		// SQL database is checked to see if this city has been searched before
		ResultSet rs = stmt.executeQuery("select * from names where input = '" + cityInput + "' collate NOCASE limit 1;");
		if (rs.next() == true)
		{
			cityCode = rs.getString("name");
		}
		else
		{	
				// If it's not in the SQL database, the name is converted to a cityCode by the API
				String cityInputMod = cityInput.replaceAll(" ", "%20");
				HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/find_places?text=" + cityInputMod + "&language=en")
						.header("X-RapidAPI-Key", "a15a997526mshbec312fcc154cf1p1ce875jsn9d47aa83f2c4")
						.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
						.asJson();
				
				cityCode = response.getBody().getArray().getJSONObject(0).getString("place_id");
				
				
				// Name is stored in the SQL so we don't ever have to look it up again
				stmt.execute("INSERT INTO names(input, name) VALUES ('" + cityInput +"', '" + cityCode + "');");
		}
		
		stmt.close();
		conn.close();
		return cityCode;
	}
	
	// The main function for Hot Location Finder
	public String[] hotLocation(String weather, double temp) throws Exception
	{	
		
		// Each town is scored based on how well it "fits" the users requirements.  A fit of 0 is a perfect fit
		String bestTown = "";
		double bestTemp = 9999;
		String bestSummary = "";
		double bestFit = 9999;
		
		String tempTown = "";
		double tempTemp = 9999;
		double tempWeather = 9999;
		JSONObject tempObj = null;
		double tempFit = 99999;
		double weatherFit = 9999;
		
		
		// Prepicked list of possible destinations
		String[] elements = {"Miami", "Austin", "Chicago", "Detroit", "San Diego", "Los Angeles", "New Orleans", "Portland", "San Fransisco", "Juno", "Anchorage", "Tempe"};   
		for (String s: elements)
		{
			tempObj = dailyQuery(cityCodeLookup(s));
			tempWeather = tempObj.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getDouble("icon");
			tempTemp = tempObj.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getDouble("temperature");
			tempTown = s;
			
			
			
			// Matching the weather is worth 15 degrees of difference
			if (weather == "Sunny")
			{
				if (tempWeather == 2 || tempWeather == 3 || tempWeather == 4 || tempWeather == 5 || tempWeather == 6)
				{
					weatherFit = 0;
				}
				else weatherFit = 15;
			
			}
			else if (weather == "Raining")
			{
				if (tempWeather == 10 || tempWeather == 11 || tempWeather == 12 || tempWeather == 13 || tempWeather == 14 || tempWeather == 15)
				{
					weatherFit = 0;
				}
				else weatherFit = 15;
			}
			else if (weather == "Snowing")
			{
				if (tempWeather == 16 || tempWeather == 17 || tempWeather == 18 || tempWeather == 19 || tempWeather == 20 || tempWeather == 21 || tempWeather == 22 || tempWeather == 23 || tempWeather == 24 || tempWeather == 25)
				{
					weatherFit = 0;
				}
				else weatherFit = 15;
			}
			
			tempFit = weatherFit + Math.abs(temp - tempTemp);
			
			// Each city's fitness is compared to the current leader, and replaces it if it's better
			if (tempFit < bestFit)
			{
				bestFit = tempFit;
				bestTown = tempTown;
				bestSummary = tempObj.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("summary");
				bestTemp = tempTemp;
			}
		}
		
		// The best fit city is returned to the GUI
		String[] results = {bestTown, Double.toString(bestTemp), bestSummary};
		return results;

	}

	

	// For testing only, this main is never called during standard operation
	public static void main(String[] args) throws Exception
	{
		Client client = new Client();
		String cityCode = client.cityCodeLookup("Los Angeles");
		System.out.println(cityCode);
		System.out.println(client.dailyQuery(cityCode).getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("weather"));
	}
}

