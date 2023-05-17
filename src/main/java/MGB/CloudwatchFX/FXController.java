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
	private Parent root;
	private Stage stage;
	private Scene scene;
	public String cityCode;
	
	@FXML
	public RadioButton weatherSunny, weatherRain, weatherSnow;
	@FXML
	public Label hotWeatherOut, hotLocationOut, hotTempOut, day1, day2, day3, day4, day5, day6, day7, summary1, summary2, summary3, summary4, summary5, summary6, summary7;
	@FXML
	public TextField hotTempIn, cityInput;
	
	
	public FXController() throws Exception
	{
			backend = new Client();
			cityCode = "san-diego";
	}


	
	public void swapToDaily(ActionEvent event) throws Exception
	{
		root = FXMLLoader.load(getClass().getResource("Daily.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);		
		stage.setScene(scene);
		stage.show();
	}
	
	public void swapToHourly(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Hourly.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);;
		stage.show();
	}
	
	public void swapToAlerts(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Alerts.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);;
		stage.show();
	}
	
	public void swapToHot(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("HotLocation.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void hotFindIt(ActionEvent event) throws IOException
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
	
	public void changeCity(ActionEvent event) throws Exception
	{
		JSONObject weatherData = backend.dailyQuery(cityCode);
		summary1.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("summary"));


			
	}
	
	
	public void updateDaily() throws Exception
	{
		JSONObject weatherData = backend.dailyQuery(cityCode);
		summary1.setText(weatherData.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("summary"));
	}

}
