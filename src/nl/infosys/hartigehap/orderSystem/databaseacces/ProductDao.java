/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.orderSystem.databaseacces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.infosys.hartigehap.orderSystem.datestore.DataStore;
import nl.infosys.hartigehap.orderSystem.domain.Product;

/**
 *
 * @author Sander van Belleghem
 */
public class ProductDao {

    private final DataStore dataStore = new DataStore();
    private final ArrayList<Product> orders = new ArrayList<>();
    private String name;
    private Double price;
    private int amount;
    private boolean newLine = true;
    private boolean paycheck;

    public ProductDao() {

    }

    public boolean addProductCheck(int tableNr) {
        dataStore.openConnection();
        ResultSet rs = dataStore.executeSQLSelectStatement("SELECT BestellingID FROM bestelling WHERE TafelNummer = " + tableNr + " AND BetaalStatus = 'WiltBetalen';");

        try {
            if (rs.next()) {
                paycheck = true;
            } else {
                paycheck = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataStore.closeConnection();
        return paycheck;
    }

    DecimalFormat formatter = new DecimalFormat("$000.00");

    public ArrayList<Product> getProduct(long code, int tableNr) {
        dataStore.openConnection();
        for (Product orderitem : orders) {
            if (code == orderitem.getCode()) {

                int amount = orderitem.getAmount();
                double price = orderitem.getPrice();

                price = price / amount;
                amount++;
                price = price * amount;
                formatter.format(price);
                orderitem.setAmount(amount);
                orderitem.setPrice(price);

                newLine = false;
            }
        }
        if (newLine == true) {

            try {

                ResultSet rs = dataStore.executeSQLSelectStatement("SELECT * FROM producten WHERE ProductID = " + code);

                while (rs.next()) {
                    name = rs.getString("Naam");
                    price = rs.getDouble("Prijs");
                    amount = 1;

                    orders.add(new Product(code, name, amount, price));
                }
            } catch (SQLException e) {
                System.err.println("Melding: " + e.getMessage());
            }
        }
        newLine = true;
        dataStore.closeConnection();

        return orders;
    }

}
