package MGB.CloudwatchFX;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;



public class Client
{
	// Returns a JSon file with daily data for the city
	public static JsonNode dailyQuery(String cityCode) throws Exception
	{		
		if (checkSQLdaily(cityCode))
		{
			JsonNode empty = new JsonNode("Not implemented yet");
			return(empty);
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/daily?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			
			System.out.println(response.getBody());
			return(response.getBody());
		}
	}
	
	// Returns a JSon file with hourly data for the city
	public static JsonNode hourlyQuery(String cityCode) throws Exception
	{		
		if (checkSQLdaily(cityCode))
		{
			JsonNode empty = new JsonNode("Not implemented yet");
			return(empty);
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/daily?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			
			System.out.println(response.getBody());
			return(response.getBody());
		}
	}
	
	
	// Finds the city code for a city written in normal English
	public static String cityCodeLookup(boolean testing) throws Exception
	{
		if (testing == true)
		{
			System.out.print("Getting information for San Diego");
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
				HttpResponse<String> name_response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/find_places?text=" + input + "&language=en")
						.header("X-RapidAPI-Key", "69273f24bfmsh45bc1a50d2ce3b0p1954b4jsn042d58a23a2d")
						.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
						.asString();
				
				System.out.println(name_response.getBody());
				return("not implemented yet");
		
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
		return false;
	}
	
	// Checks if SQL database already has the hourly information for this city
	public static boolean checkSQLhourly(String cityInput)
	{
		return false;
	}
	

	public static void main(String[] args) throws Exception
	{
        String url = "jdbc:sqlite:./src/main/resources/CloudData.db";

        try (Connection conn = DriverManager.getConnection(url))
        {
            if (conn != null)
            {
                System.out.println("A connected to database.");
            }

        } 
        
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        
        
		String cityCode = cityCodeLookup(true);
		System.out.print(dailyQuery(cityCode));
		System.out.print(hourlyQuery(cityCode));
	}
}

