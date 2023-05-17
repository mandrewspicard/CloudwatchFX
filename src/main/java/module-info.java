module MGB.CloudwatchFX {
    requires javafx.controls;
	requires java.sql;
	requires unirest.java;
	requires json.simple;
	requires java.desktop;
	requires javafx.fxml;
	requires json;
	
    opens MGB.CloudwatchFX to javafx.fxml;
	
	
    exports MGB.CloudwatchFX;
}
