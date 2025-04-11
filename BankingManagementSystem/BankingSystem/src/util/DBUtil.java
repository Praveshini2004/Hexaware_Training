/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author DELL
 */;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getDBConn() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/BankSystem"; 
        String user = "root";                                   
        String password = "Praveshini";                         

        return DriverManager.getConnection(url, user, password);
    }
}

