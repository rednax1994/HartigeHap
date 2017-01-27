/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.orderSystem.databaseacces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import nl.infosys.hartigehap.orderSystem.datestore.DataStore;
import nl.infosys.hartigehap.orderSystem.domain.Product;

/**
 *
 * @author devc0n
 */
public class OrderDao {

    private final DataStore dataStore = new DataStore();

    public OrderDao() {

    }

    public ArrayList<Product> insertOrder(int tableNr, ArrayList<Product> orders) {

        try {

            dataStore.openConnection();

            int insertedId = dataStore.executeSQLInsertStatement("INSERT INTO `bestelling` (`TafelNummer`) VALUES (" + tableNr + ")");

            for (Product orderitem : orders) {
                long code = orderitem.getCode();
                int amount = orderitem.getAmount();

                dataStore.executeSQLInsertStatement("INSERT INTO `bestelregel` (fk_BestellingID, fk_ProductID, Hoeveelheid) VALUES (" + insertedId + "," + code + "," + amount + ")");
            }

            dataStore.closeConnection();

        } catch (Exception e) {
            System.err.println("Melding: " + e.getMessage());
        }

        return null;
    }

    public void updateCheckOut(int tableNr) {
        try {
            dataStore.openConnection();

            if (tableNr > 0) {
                dataStore.executeSQLUpdateStatement("UPDATE bestelling SET `BetaalStatus` = 'WiltBetalen' WHERE `TafelNummer` = " + tableNr + " AND BetaalStatus = 'NietBetaald'");
            }

            dataStore.closeConnection();
        } catch (Exception e) {
            System.err.println("Melding: " + e.getMessage());
        }
    }

    public void getOrders(int tableNr) {
        try {
            dataStore.openConnection();

            ResultSet order = dataStore.executeSQLSelectStatement("SELECT BestellingID FROM bestelling WHERE `TafelNummer` = " + tableNr + " AND BetaalStatus = 'NietBetaald'");

            while (order.next()) {
                int orderId = order.getInt("BestellingID");
            }

            ResultSet orderitem = dataStore.executeSQLSelectStatement("SELECT BestellingID FROM bestelling WHERE `TafelNummer` = " + tableNr + " AND BetaalStatus = 'NietBetaald'");

            dataStore.closeConnection();

        } catch (SQLException e) {
            System.err.println("Melding: " + e.getMessage());
        }
    }
}
