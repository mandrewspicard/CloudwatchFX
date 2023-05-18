package MGB.CloudwatchFX;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Client
{
	
	private static Statement stmt;
	
	public Client() throws Exception
	{
	
    String url = "jdbc:sqlite:./src/main/resources/CloudData.db";

    Connection conn = DriverManager.getConnection(url);
    stmt  = conn.createStatement();
    
	}
	// Returns a JSon file with daily data for the city
	public static JSONObject dailyQuery(String cityCode) throws Exception
	{		
		if (checkSQLdaily(cityCode))
		{
			 ResultSet rs = stmt.executeQuery("select * from dailyData;");
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 return outputJson.getObject();
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/daily?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			
			System.out.println("Didn't find in SQL");
			return(response.getBody().getObject());
		}
	}
	
	// Returns a JSon file with hourly data for the city
	public static JSONObject hourlyQuery(String cityCode) throws Exception
	{		
		if (checkSQLdaily(cityCode))
		{
			 ResultSet rs = stmt.executeQuery("select * from hourlyData;");
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 return outputJson.getObject();
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/hourly?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			
			System.out.println("Didn't find in SQL");
			return response.getBody().getObject();
		}
	}
	
	// Returns a JSon file with hourly data for the city
	public static JSONObject alertsQuery(String cityCode) throws Exception
	{		
		if (checkSQLdaily(cityCode))
		{
			 ResultSet rs = stmt.executeQuery("select * from alerts;");
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 return outputJson.getObject();
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/alerts?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			
			System.out.println("Didn't find in SQL");
			return response.getBody().getObject();
		}
	}
	
	
	// Finds the city code for a city written in normal English
	public String cityCodeLookup(String cityInput) throws Exception
	{
		String cityCode = "";
		ResultSet rs = stmt.executeQuery("select * from names where input = '" + cityInput + "' collate NOCASE limit 1;");
		if (rs.next() == true) cityCode = rs.getString("name");
		else
		{	
				HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/find_places?text=" + cityInput + "&language=en")
						.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
						.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
						.asJson();
				
				cityCode = response.getBody().getObject().getJSONArray("").getJSONObject(0).getString("place_id");
		}
		
		return cityCode;
	}
		
	
	// Checks if SQL database already has the city code
	public static boolean checkSQLname(String cityInput) throws Exception
	{
		 ResultSet rs = stmt.executeQuery("select * from names where input = " + cityInput + " collate NOCASE;");
		 if (rs.next() == false) return false;
		 else return true;
	}
	
	// Checks if SQL database already has the daily information for this city
	public static boolean checkSQLdaily(String cityInput) throws Exception
	{
		return true;
	}
	
	// Checks if SQL database already has the hourly information for this city
	public static boolean checkSQLhourly(String cityInput) throws Exception
	{
		return true;
	}
	
	// Checks if SQL database already has the hourly information for this city
	public static boolean checkSQLalerts(String cityInput) throws Exception
	{
		return true;
	}

	public static void main(String[] args) throws Exception
	{
		Client client = new Client();
		String cityCode = client.cityCodeLookup("ur mum");
		System.out.println(cityCode);
		//System.out.println(dailyQuery(cityCode).getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("weather"));
	}
}

