package MGB.CloudwatchFX;

import java.io.IOException;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.*;

public class FXController
{
	public Client backend;
	JSONObject weatherData;
	private Parent root;
	private Stage stage;
	private Scene scene;
	public String cityCode;
	
	@FXML
	public RadioButton weatherSunny, weatherRain, weatherSnow;
	@FXML
	public Label hotWeatherOut, hotLocationOut, hotTempOut, day1, day2, day3, day4, day5, day6, day7, summary1, summary2, summary3, summary4, summary5, summary6, summary7, alertBox;
	@FXML
	public Label time1, time2, time3, time4, time5, time6, time7, temp1, temp2, temp3, temp4, temp5, temp6, temp7, weather1, weather2, weather3, weather4, weather5, weather6, weather7;
	
	@FXML
	public TextField hotTempIn, cityInput;
	
	
	
	
	public FXController() throws Exception
	{
			backend = new Client();
			cityCode = "san-diego";
			weatherData = backend.dailyQuery(cityCode);
	}


	
	public void swapToDaily(ActionEvent event) throws Exception
	{

		weatherData = backend.dailyQuery(cityCode);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Daily.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		
		   		
		FXController controller = loader.getController();
		

		controller.day1.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("day").substring(6));
		controller.day2.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(1).getString("day").substring(6));
		controller.day3.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(2).getString("day").substring(6));
		controller.day4.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(3).getString("day").substring(6));
		controller.day5.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(4).getString("day").substring(6));
		controller.day6.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(5).getString("day").substring(6));
		controller.day7.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(6).getString("day").substring(6));
		
		
		controller.summary1.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("summary"));
		controller.summary2.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(1).getString("summary"));
		controller.summary3.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(2).getString("summary"));
		controller.summary4.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(3).getString("summary"));
		controller.summary5.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(4).getString("summary"));
		controller.summary6.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(5).getString("summary"));
		controller.summary7.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(6).getString("summary"));
		
		
		
		stage.show();
	}
	
	public void swapToHourly(ActionEvent event) throws Exception
	{
		weatherData = backend.hourlyQuery(cityCode);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Hourly.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		
		   		
		FXController controller = loader.getController();
		
		controller.time1.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getString("date").substring(11,16));
		controller.time2.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getString("date").substring(11,16));
		controller.time3.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getString("date").substring(11,16));
		controller.time4.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getString("date").substring(11,16));
		controller.time5.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getString("date").substring(11,16));
		controller.time6.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(5).getString("date").substring(11,16));
		controller.time7.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(6).getString("date").substring(11,16));
		
		controller.temp1.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getDouble("temperature")) + "°F");
		controller.temp2.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getDouble("temperature")) + "°F");
		controller.temp3.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getDouble("temperature")) + "°F");
		controller.temp4.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getDouble("temperature")) + "°F");
		controller.temp5.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getDouble("temperature")) + "°F");
		controller.temp6.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(5).getDouble("temperature")) + "°F");
		controller.temp7.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(6).getDouble("temperature")) + "°F");
		
		controller.weather1.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getString("summary"));
		controller.weather2.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getString("summary"));
		controller.weather3.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getString("summary"));
		controller.weather4.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getString("summary"));
		controller.weather5.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getString("summary"));
		controller.weather6.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(5).getString("summary"));
		controller.weather7.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(6).getString("summary"));
		
		

		
		stage.show();
	}
	
	public void swapToAlerts(ActionEvent event) throws Exception
	{
		weatherData = backend.alertsQuery(cityCode);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Alerts.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene); 		
		FXController controller = loader.getController();
		
		if (weatherData.getJSONObject("alerts").getJSONArray("data").length() != 0)
		{
			String Alerts = "";
			for (int i = 0; i < weatherData.getJSONObject("alerts").getJSONArray("data").length(); i++)
			{
			Alerts += weatherData.getJSONObject("alerts").getJSONArray("data").getJSONObject(i).getString("headline") + "\n";
			}
			controller.alertBox.setText(Alerts);
		}
		
		
		stage.show();
	}
	
	public void swapToHot(ActionEvent event) throws Exception
	{
		root = FXMLLoader.load(getClass().getResource("HotLocation.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void hotFindIt(ActionEvent event) throws Exception
	{
		if (weatherSunny.isSelected())
		{
			hotWeatherOut.setText("Sunny");
		}
		else if (weatherRain.isSelected())
		{
			hotWeatherOut.setText("Raining");
		}
		else if (weatherSnow.isSelected())
		{
			hotWeatherOut.setText("Snowing");
		}
		
		hotTempOut.setText(hotTempIn.getText() + "°F");
		hotLocationOut.setText("Utopia");
			
	}
	
	public void changeCityDaily(ActionEvent event) throws Exception
	{
		cityCode = backend.cityCodeLookup(cityInput.getText());	
		weatherData = backend.dailyQuery(cityCode);
		
		day1.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("day").substring(6));
		day2.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(1).getString("day").substring(6));
		day3.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(2).getString("day").substring(6));
		day4.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(3).getString("day").substring(6));
		day5.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(4).getString("day").substring(6));
		day6.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(5).getString("day").substring(6));
		day7.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(6).getString("day").substring(6));
		
		summary1.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("summary"));
		summary2.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(1).getString("summary"));
		summary3.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(2).getString("summary"));
		summary4.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(3).getString("summary"));
		summary5.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(4).getString("summary"));
		summary6.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(5).getString("summary"));
		summary7.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(6).getString("summary"));
	}
	
	public void changeCityHourly(ActionEvent event) throws Exception
	{
		cityCode = backend.cityCodeLookup(cityInput.getText());	
		weatherData = backend.hourlyQuery(cityCode);
		
		time1.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getString("date").substring(11,16));
		time2.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getString("date").substring(11,16));
		time3.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getString("date").substring(11,16));
		time4.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getString("date").substring(11,16));
		time5.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getString("date").substring(11,16));
		time6.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(5).getString("date").substring(11,16));
		time7.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(6).getString("date").substring(11,16));
		
		temp1.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getDouble("temperature")) + "°F");
		temp2.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getDouble("temperature")) + "°F");
		temp3.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getDouble("temperature")) + "°F");
		temp4.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getDouble("temperature")) + "°F");
		temp5.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getDouble("temperature")) + "°F");
		temp6.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(5).getDouble("temperature")) + "°F");
		temp7.setText(String.valueOf((int)weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(6).getDouble("temperature")) + "°F");
		
		weather1.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getString("summary"));
		weather2.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getString("summary"));
		weather3.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getString("summary"));
		weather4.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getString("summary"));
		weather5.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getString("summary"));
		weather6.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(5).getString("summary"));
		weather7.setText(weatherData.getJSONObject("hourly").getJSONArray("data").getJSONObject(6).getString("summary"));
	}
	
	public void changeCityAlerts(ActionEvent event) throws Exception
	{
		cityCode = backend.cityCodeLookup(cityInput.getText());
		weatherData = backend.alertsQuery(cityCode);
		
		if (weatherData.getJSONObject("alerts").getJSONArray("data").length() != 0)
		{
			String Alerts = "";
			for (int i = 0; i < weatherData.getJSONObject("alerts").getJSONArray("data").length(); i++)
			{
			Alerts += weatherData.getJSONObject("alerts").getJSONArray("data").getJSONObject(i).getString("headline") + "\n";
			}
			alertBox.setText(Alerts);
		}
	}
}
