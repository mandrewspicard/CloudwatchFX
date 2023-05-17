package MGB.CloudwatchFX;

import javafx.application.Application;
import javafx.scene.Parent;
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
    		Parent root = FXMLLoader.load(getClass().getResource("Daily.fxml"));
    		Scene scene = new Scene(root);
    		
    		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Logo2.jpg")));
    		
    		primaryStage.setTitle("Cloudwatch");
    		primaryStage.setScene(scene);
    		primaryStage.setResizable(false);
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