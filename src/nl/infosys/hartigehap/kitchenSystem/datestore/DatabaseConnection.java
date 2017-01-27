package nl.infosys.hartigehap.kitchenSystem.datestore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import nl.infosys.hartigehap.mainSystem.datebaseacces.mainSystemDao;
import nl.infosys.hartigehap.orderSystem.datestore.DataStore;

/**
 *
 * @author Infosys
 */
public class DatabaseConnection {

    Connection con;
    Statement stmt;

    public DatabaseConnection() {
        getConnection();
    }

    public void getConnection() {
        try {

            Properties properties = new Properties();
            properties.load(mainSystemDao.class.getResourceAsStream("config.properties"));

            // Setup an connection
            con = DriverManager.getConnection(properties.getProperty("database"), properties.getProperty("dbuserC3"), properties.getProperty("dbpasswordC3"));

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

    public void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println("Melding: " + e.getMessage());
        }
    }

    //A method for using SELECT queries.
    public ResultSet ExecuteDB(String QueryTekst) {
        ResultSet rs1 = null;
        try {
            rs1 = stmt.executeQuery(QueryTekst);
        } catch (Exception e) {
            System.err.println("Melding1: " + e.getMessage());
        }
        return rs1;
    }

    //A method for using INSERT and UPDATE queries.
    public void UpdateDB(String QueryTekst) {
        try {
            stmt.executeUpdate(QueryTekst);
            con.close();
        } catch (Exception e) {
            System.err.println("Melding1: " + e.getMessage());
        }
    }

}
