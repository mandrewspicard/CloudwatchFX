package MGB.CloudwatchFX;


import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

public class Client
{
	// Returns a JSon file with daily data for the city
	public static JsonNode dailyQuery(String cityCode, Statement stmt) throws Exception
	{		
		if (checkSQLdaily(cityCode))
		{
			 ResultSet rs = stmt.executeQuery("select * from dailyData;");
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 System.out.print(outputJson);
			 return outputJson;
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/daily?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			
			System.out.println("Didn't find in SQL");
			System.out.println(response.getBody());
			return(response.getBody());
		}
	}
	
	// Returns a JSon file with hourly data for the city
	public static JsonNode hourlyQuery(String cityCode, Statement stmt) throws Exception
	{		
		if (checkSQLdaily(cityCode))
		{
			 ResultSet rs = stmt.executeQuery("select * from hourlyData;");
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 System.out.print(outputJson);
			 return outputJson;
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/hourly?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			
			System.out.println("Didn't find in SQL");
			return(response.getBody());
		}
	}
	
	// Returns a JSon file with hourly data for the city
	public static JsonNode alertsQuery(String cityCode, Statement stmt) throws Exception
	{		
		if (checkSQLdaily(cityCode))
		{
			 ResultSet rs = stmt.executeQuery("select * from alerts;");
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 System.out.print(outputJson);
			 return outputJson;
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/alerts?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			
			System.out.println("Didn't find in SQL");
			return(response.getBody());
		}
	}
	
	
	// Finds the city code for a city written in normal English
	public static String cityCodeLookup(boolean testing) throws Exception
	{
		if (testing == true)
		{
			System.out.println("Getting information for San Diego");
			return("san-diego");
		}

		else
		{
			Scanner keyboard = new Scanner(System.in);
			System.out.print("Enter a city name: ");
			String input = keyboard.nextLine();
			input = input.replaceAll(" ", "%20");
			
			keyboard.close();
			
			if (checkSQLname(input) == true)
			{
				return("not implemented yet");
			}
		
			else 
			{
				HttpResponse<String> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/find_places?text=" + input + "&language=en")
						.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
						.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
						.asString();
				
				System.out.println("New town...");
				return(response.getBody());
		
			}
		}
	}
	
	// Checks if SQL database already has the city code
	public static boolean checkSQLname(String cityInput)
	{
		return false;
	}
	
	// Checks if SQL database already has the daily information for this city
	public static boolean checkSQLdaily(String cityInput)
	{
		return true;
	}
	
	// Checks if SQL database already has the hourly information for this city
	public static boolean checkSQLhourly(String cityInput)
	{
		return true;
	}
	
	// Checks if SQL database already has the hourly information for this city
	public static boolean checkSQLalerts(String cityInput)
	{
		return true;
	}

	public static void main(String[] args) throws Exception
	{
        String url = "jdbc:sqlite:./src/main/resources/CloudData.db";

        Connection conn = DriverManager.getConnection(url);
        Statement stmt  = conn.createStatement();
        System.out.println("Connected to database...");

        
        
		String cityCode = cityCodeLookup(true);
		System.out.println(dailyQuery(cityCode, stmt));
		//System.out.println(hourlyQuery(cityCode, stmt));
		//System.out.println(alertsQuery(cityCode, stmt));
	}
}

