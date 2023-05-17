package MGB.CloudwatchFX;

import java.io.IOException;

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
	Parent root;
	Stage stage;
	Scene scene;
	
	@FXML
	private RadioButton weatherSunny, weatherRain, weatherSnow;
	@FXML
	private Label hotWeatherOut, hotLocationOut, hotTempOut;
	@FXML
	private TextField hotTempIn;

	
	public void swapToDaily(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Daily.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);;
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

}
