/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.databaseacces;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import nl.infosys.hartigehap.mainSystem.datebaseacces.mainSystemDao;

/**
 *
 * @author IVP3C2
 */
public class ConnectionDB {

    private Connection con; //Interface java.sql package
    private Statement stmt;

    /**
     *
     */
    public ConnectionDB() {

        try {

            Properties properties = new Properties();
            properties.load(mainSystemDao.class.getResourceAsStream("config.properties"));

            con = null;
            //initialiseren van con object; 
            con = DriverManager.getConnection(properties.getProperty("database"), properties.getProperty("dbuserC2"), properties.getProperty("dbpasswordC2"));
            //Succes melding als connectie naar MySQL database is gelukt
            if (!con.isClosed()) {
                System.out.println("Successfully connected to "
                        + "MySQL server using TCP/IP...");
            }

            stmt = con.createStatement();
        } catch (SQLException | IOException e) {
            System.err.println("Melding: " + e.getMessage());
        }
    }

    /**
     * Voer een update query uit
     *
     * @param query
     * @return boolean
     * @throws java.sql.SQLException
     */
    public boolean executeUpdate(String query) throws SQLException {
        boolean result;

        try {
            stmt.executeUpdate(query);

            result = true;
        } catch (SQLException e) {
            System.err.println("Melding: " + e.getMessage());
            result = false;
        }

        // Geeft true (uitgevoerd) of false (niet uitgevoerd) terug
        return result;
    }

    /**
     * Voer een execute query uit
     *
     * @param query
     * @return ResultSet
     * @throws java.sql.SQLException
     */
    public ResultSet executeQuery(String query) throws SQLException {
        // Voor een execute query uit
        return stmt.executeQuery(query);
    }

    /**
     * Sluit het statement en de database connectie
     */
    public void close() {
        try {
            this.stmt.close();
            this.con.close();
        } catch (SQLException e) {
            System.err.println("SQL Foutmelding: " + e.getMessage());
        }
    }
}
