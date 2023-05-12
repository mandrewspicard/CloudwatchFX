package MGB.CloudwatchFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // Create UI elements
        Label townName = new Label("Town Name");
        Label swapHourly = new Label("Swap to Hourly");
        Label changeCity = new Label("Change City");
        Label hotLocationFinder = new Label("Hot Location Finder");
        Label checkAlerts = new Label("Check Alerts");
        Label monday = new Label("Monday");
        Label tuesday = new Label("Tuesday");
        Label wednesday = new Label("Wednesday");
        Label thursday = new Label("Thursday");
        Label friday = new Label("Friday");
        Label saturday = new Label("Saturday");
        Label sunday = new Label("Sunday");
        Label refreshData = new Label("Refresh Data");
        
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
        townNameBox.getChildren().addAll(townName, swapHourly);
        townNameBox.setSpacing(10);
        
        VBox sidebar = new VBox();
        sidebar.getChildren().addAll(changeCity, hotLocationFinder, checkAlerts);
        sidebar.setSpacing(10);
        
        VBox weekdays = new VBox();
        weekdays.getChildren().addAll(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        weekdays.setSpacing(10);
        
        HBox refreshDataBox = new HBox();
        refreshDataBox.getChildren().add(refreshData);
        refreshDataBox.setStyle("-fx-alignment: center-right;");
        
        GridPane content = new GridPane();
        content.setHgap(10);
        content.setVgap(10);
        content.setPadding(new Insets(20));
        content.add(townNameBox, 0, 0);
        content.add(sidebar, 0, 1);
        content.add(weekdays, 1, 0, 1, 2);
        
        root.getChildren().addAll(content, refreshDataBox);
        
        // Set up the scene and show the stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My GUI");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}