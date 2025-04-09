/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author DELL
 */
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    public static String getPropertyString() {
        String connectionString = "";
        try {
            Properties props = new Properties();
            InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("C:\\Users\\DELL\\OneDrive\\Documents\\NetBeansProjects\\HospitalManagement\\src\\util\\db.properties");

            if (input == null) {
                System.out.println("Unable to find db.properties file.");
                return null;
            }

            props.load(input);

            // Reading individual properties
            String hostname = props.getProperty("db.hostname");
            String port = props.getProperty("db.port");
            String dbname = props.getProperty("db.dbname");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            // Build JDBC connection string (for MySQL)
            connectionString = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connectionString;
    }
}
