/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.orderSystem.businesslogic;

import java.util.ArrayList;
import nl.infosys.hartigehap.orderSystem.domain.Product;

/**
 *
 * @author Sander van Belleghem
 */
public interface ImmutableOrderManager {

    public ArrayList<Product> getOrders();

    public boolean deleteProduct(long code);

    public void placeOrder(int tableNr);

    public double getTotalPrice();

    public void clearOrder();

    public void clearTotalOrder();

    public ArrayList<Product> getTotalOrder();

    public boolean addProductCheck(int tableNr);

    public void checkOut(int tableNr);

    public void addProduct(long code, int tableNr);

    public ArrayList<Product> getOrder();

}
