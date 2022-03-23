/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author pvand
 */
public class DAO {

    public static Connection conn;

    public DAO() {
        boolean checkDB = getDBConnection();
        if (!checkDB) {
            System.out.println("DB failed");
            return;
        }
    }
        private boolean getDBConnection() {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/electronic";
        String dbClass = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(dbClass);
            conn = (Connection) DriverManager.getConnection(dbUrl, "root", "vuhoang2016");
            System.out.println("this connection is successful");
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    } 
}
