package MGB.CloudwatchFX;

import org.json.JSONObject;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.image.*;


public class App extends Application{

    @Override
    public void start(Stage primaryStage)
    {
    	try
    	{
    		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Logo2.jpg")));
    		primaryStage.setResizable(false);
    		primaryStage.setTitle("Cloudwatch");
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Daily.fxml"));
    		Parent root = loader.load();
    		Scene scene = new Scene(root);
    		primaryStage.setScene(scene);
    		
    		   		
    		FXController controller = loader.getController();
    		
    		JSONObject weatherData = controller.weatherData;
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
    		

    		primaryStage.show();
    		
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	
    }
    	
    
    public static void main(String[] args) {
        launch(args);
    }

}