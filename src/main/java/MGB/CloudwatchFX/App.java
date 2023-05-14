package MGB.CloudwatchFX;

import java.awt.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;



//public class App extends Application {
	
public class App extends Application implements EventHandler<ActionEvent>{
	
	 Button button;
	 Button button1;
	 Button button2;
	 Button button3;
	 Button button4;
	 
	// Stage window;
	// Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	//window = primaryStage;
        
        // Create UI elements
        Label townName = new Label("Town Name");
        Label swapHourly = new Label("Swap to Hourly");  				// button
        Label changeCity = new Label("Change City"); 					// button
        Label hotLocationFinder = new Label("Hot Location Finder");		// button
        Label checkAlerts = new Label("Check Alerts");					// button
        Label monday = new Label("Monday");
        Label tuesday = new Label("Tuesday");
        Label wednesday = new Label("Wednesday");
        Label thursday = new Label("Thursday");
        Label friday = new Label("Friday");
        Label saturday = new Label("Saturday");
        Label sunday = new Label("Sunday");
        Label refreshData = new Label("Refresh Data");					// button
        
       button  = new Button();
       button.setText("Swap to Hourly");
       
       //This class handle button events
       button.setOnAction(new EventHandler<ActionEvent>() {
    	   public void handle(ActionEvent event) {
    		   System.out.println("Changed to: Swap to hourly");
    	   }
       });
       
       button1 = new Button();
       button1.setText("Change City");
       
       //This class is more compact using Lambda expressions.
       button1.setOnAction(e -> System.out.println("Changed to: Change City"));
       
       button2 = new Button();
       button2.setText("Hot Location Finder");
       button2.setOnAction(this);
       
       button3 = new Button();
       button3.setText("Check Alerts"); 
       button3.setText("Check Alerts");         
       button3.setOnAction(e -> System.out.println("Changed to: Check Alerts"));
       //button3.setOnAction(e -> window.setScene(scene2));
       
       button4 = new Button();
       button4.setText("Refresh Data");
       button4.setOnAction(this);
      
        
        
        // Set CSS styles for the UI elements
        townName.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        swapHourly.setStyle("-fx-text-fill: blue;");
        changeCity.setStyle("-fx-text-fill: blue;");
        hotLocationFinder.setStyle("-fx-text-fill: blue;");
        checkAlerts.setStyle("-fx-text-fill: blue;");
        refreshData.setStyle("-fx-text-fill: blue;");
        
        // Create layouts and add UI elements to them
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        
        VBox townNameBox = new VBox();
       // townNameBox.getChildren().addAll(townName, swapHourly);
        townNameBox.getChildren().addAll(townName, button);
        townNameBox.setSpacing(10);
       // townNameBox.getChildren().add(button);
        
        
        VBox sidebar = new VBox();
       // sidebar.getChildren().addAll(changeCity, hotLocationFinder, checkAlerts);
        sidebar.getChildren().addAll(button1, button2, button3);
        sidebar.setSpacing(10);
        //sidebar.getChildren().add(button1);
       // sidebar.getChildren().add(button2);
       // sidebar.getChildren().add(button3);
      //  scene1 = new Scene(sidebar, 200, 200);
        
      //  button2.setOnAction(e -> window.setScene(scene1));
      // layout2 = new StackPane();
      //  layout2.getChildren().add(button2);
      //  scene2 = new Scene(layout2, 400, 300);
        
        VBox weekdays = new VBox();
        weekdays.getChildren().addAll(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        weekdays.setSpacing(10);
        
        HBox refreshDataBox = new HBox();
        //refreshDataBox.getChildren().add(refreshData);
        refreshDataBox.getChildren().add(button4);
        refreshDataBox.setStyle("-fx-alignment: center-right;");
       // refreshDataBox.getChildren().add(button4);
        
        GridPane content = new GridPane();
        content.setHgap(10);
        content.setVgap(10);
        content.setPadding(new Insets(20));
        content.add(townNameBox, 0, 0);
        content.add(sidebar, 0, 1);
        content.add(weekdays, 1, 0, 1, 2);
        
      
        
        root.getChildren().addAll(content, refreshDataBox);
        
        // Set up the scene and show the stage
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cloudwatch");
        primaryStage.show();					// Display to the user
        
       // window.setScene(scene1);
       // window.setTitle("Cloud");
       // window.show();
        
    }
public void handle(ActionEvent event) {
	

	
	if(event.getSource()==button2) {
		System.out.println(" You click to: Hot Location Finder");
	}
	
	//if(event.getSource()==button3) {
	//	System.out.println(" You click to: Check Alerts");
	//}
	
	if(event.getSource()==button4) {
		System.out.println(" You click to: Refresh Data");
	}
	

}
    public static void main(String[] args) {
        launch(args);
    }
}