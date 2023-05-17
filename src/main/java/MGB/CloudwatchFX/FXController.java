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
	public Label hotWeatherOut, hotLocationOut, hotTempOut, day1, day2, day3, day4, day5, day6, day7, summary1, summary2, summary3, summary4, summary5, summary6, summary7;
	@FXML
	public TextField hotTempIn, cityInput;
	
	
	public FXController() throws Exception
	{
			backend = new Client();
			cityCode = "san-diego";
    		weatherData = backend.dailyQuery("cityCode");
	}


	
	public void swapToDaily(ActionEvent event) throws Exception
	{
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
	
	public void swapToHourly(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Hourly.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		

		
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
