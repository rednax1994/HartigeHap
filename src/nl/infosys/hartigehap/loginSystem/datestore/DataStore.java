/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.loginSystem.datestore;

/**
 *
 * @author Sander van Belleghem
 */
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import nl.infosys.hartigehap.mainSystem.datebaseacces.mainSystemDao;

public class DataStore {

    Connection con; //Interface java.sql package
    Statement stmt; //Interface java.sql package

    ResultSet rs; //Interface java.sql package

    private int insertedId;

    public DataStore() {

        stmt = null;
    }

    public void openConnection() {
        try {

            Properties properties = new Properties();
            properties.load(mainSystemDao.class.getResourceAsStream("config.properties"));

            // Setup an connection
            con = DriverManager.getConnection(properties.getProperty("database"), properties.getProperty("dbuserC1"), properties.getProperty("dbpasswordC1"));

            // stmt initialiseren 
            stmt = con.createStatement();

            //Succes melding als connectie naar MySQL database is gelukt
            if (!con.isClosed()) {
                System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
            }
        } catch (SQLException | IOException e) {
            System.err.println("Melding: " + e.getMessage());
        }
    }

    public int executeSQLInsertStatement(String query) {
        try {
            stmt.execute(query, Statement.RETURN_GENERATED_KEYS);

            // Haal de generated keys op
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Melding: " + e.getMessage());
        }

        return insertedId;
    }

    public ResultSet executeSQLSelectStatement(String query) {
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Melding: " + e.getMessage());
        }
        return rs;
    }

    public void executeSQLUpdateStatement(String query) {
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Melding: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Melding: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Melding: " + e.getMessage());
            }
        }
    }

}
