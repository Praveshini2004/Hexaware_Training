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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // ✅ Path to db.properties
                String filePath = "C:/Users/DELL/OneDrive/Documents/NetBeansProjects/HospitalManagementSystem/src/util/db.properties";
                Properties props = PropertyUtil.getProperties(filePath);

                String driver = props.getProperty("db.driver");
                String hostname = props.getProperty("db.hostname");
                String port = props.getProperty("db.port");
                String dbname = props.getProperty("db.dbname");
                String username = props.getProperty("db.username");
                String password = props.getProperty("db.password");

                String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname +
                        "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

                Class.forName(driver); // Load MySQL driver
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("✅ Database connected successfully.");

            } catch (SQLException | ClassNotFoundException e) {
                System.err.println("❌ Failed to connect to database.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
