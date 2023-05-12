package MGB.CloudwatchFX;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerSetup
{
    public static void createNewDatabase(String fileName)
    {

        String url = "jdbc:sqlite:./src/main/resources/" + fileName;

        try (Connection conn = DriverManager.getConnection(url))
        {
            if (conn != null)
            {
                System.out.println("Connected to database.");
            }

        }
        
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        

    }
    
    public static void createNewTable()
    {
        // SQLite connection string
        String url = "jdbc:sqlite:./src/main/resources/CloudData.db";
        
        // SQL statement for creating a new table
        String sql = "create table IF NOT EXISTS hourlyData (\n"
        	    + "_id bigint PRIMARY KEY,\n"
        		+ "cityCode text NOT NULL,\n"
        	    + "data TEXT NOT NULL\n"
        	    + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Table created");
    }

    
    public static void main(String[] args)
    {
        createNewTable();
    }
    
}
