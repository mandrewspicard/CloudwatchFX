module MGB.CloudwatchFX {
    requires javafx.controls;
	requires java.sql;
	requires unirest.java;
	requires json.simple;
	requires java.desktop;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
	requires javafx.fxml;
	
    opens MGB.CloudwatchFX to javafx.fxml;
	
	
    exports MGB.CloudwatchFX;
}
