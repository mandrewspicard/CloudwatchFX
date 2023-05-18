package MGB.CloudwatchFX;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.json.JSONObject;

import java.sql.ResultSet;

public class Client
{
	
	// Returns a JSon file with daily data for the city
	public static JSONObject dailyQuery(String cityCode) throws Exception
	{
		Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/CloudData.db");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select data from dailyData where cityCode = '" + cityCode + "' limit 1;");
		if (rs.next() == true)
		{
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 stmt.close();
			 
			 return outputJson.getObject();
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/daily?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "a15a997526mshbec312fcc154cf1p1ce875jsn9d47aa83f2c4")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			

			stmt.execute("INSERT INTO dailyData(cityCode, data) VALUES ('" + cityCode +"', '" + response.getBody() + "');");
			stmt.close();
			conn.close();
			
			return(response.getBody().getObject());
		}
	}
	
	// Returns a JSon file with hourly data for the city
	public static JSONObject hourlyQuery(String cityCode) throws Exception
	{	
		Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/CloudData.db");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select data from hourlyData where cityCode = '" + cityCode + "' limit 1;");
		if (rs.next() == true)
		{
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 stmt.close();
			 return outputJson.getObject();
		}
		
		else
		{
			HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/hourly?place_id=" + cityCode + "&language=en&units=auto")
					.header("X-RapidAPI-Key", "a15a997526mshbec312fcc154cf1p1ce875jsn9d47aa83f2c4")
					.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
					.asJson();
			

			stmt.execute("INSERT INTO hourlyData(cityCode, data) VALUES ('" + cityCode +"', '" + response.getBody() + "');");
			stmt.close();
			conn.close();
			
			return(response.getBody().getObject());
		}
	}
	
	// Returns a JSon file with alert data for the city
	public static JSONObject alertsQuery(String cityCode) throws Exception
	{	

		Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/CloudData.db");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select data from alerts where cityCode = '" + cityCode + "' limit 1;");
		if (rs.next() == true)
		{
			 JsonNode outputJson = new JsonNode(rs.getString("data"));
			 stmt.close();
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
		ResultSet rs = stmt.executeQuery("select * from names where input = '" + cityInput + "' collate NOCASE limit 1;");
		if (rs.next() == true)
		{
			cityCode = rs.getString("name");
		}
		else
		{	
				String cityInputMod = cityInput.replaceAll(" ", "%20");
				HttpResponse<JsonNode> response = Unirest.get("https://ai-weather-by-meteosource.p.rapidapi.com/find_places?text=" + cityInputMod + "&language=en")
						.header("X-RapidAPI-Key", "a15a997526mshbec312fcc154cf1p1ce875jsn9d47aa83f2c4")
						.header("X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com")
						.asJson();
				
				cityCode = response.getBody().getArray().getJSONObject(0).getString("place_id");
				
				stmt.execute("INSERT INTO names(input, name) VALUES ('" + cityInput +"', '" + cityCode + "');");
		}
		
		stmt.close();
		conn.close();
		return cityCode;
	}

	// For testing only
	public static void main(String[] args) throws Exception
	{
		Client client = new Client();
		String cityCode = client.cityCodeLookup("Los Angeles");
		System.out.println(cityCode);
		System.out.println(dailyQuery(cityCode).getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("weather"));
	}
}

