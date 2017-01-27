/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.orderSystem.businesslogic;

import nl.infosys.hartigehap.orderSystem.domain.Product;
import nl.infosys.hartigehap.orderSystem.domain.ImmutableProduct;
import java.util.ArrayList;
import nl.infosys.hartigehap.orderSystem.databaseacces.OrderDao;
import nl.infosys.hartigehap.orderSystem.databaseacces.ProductDao;

/**
 *
 * @author devc0n 
 *
 */
public class OrderManager implements ImmutableOrderManager {

    private OrderDao orderDao;
    private ProductDao productDao;
    private ArrayList<Product> totalOrder;
    private ArrayList<Product> order;
    private double totalPrice;

    public OrderManager() {

        orderDao = new OrderDao();
        productDao = new ProductDao();
        totalOrder = new ArrayList<>();
        order = new ArrayList<>();

    }

    //NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    @Override
    public ArrayList<Product> getOrders() {
        return order;
    }

    @Override
    public ArrayList<Product> getTotalOrder() {
        return totalOrder;
    }

    @Override
    public boolean deleteProduct(long code) {
        
        for (ImmutableProduct orderitem : order) {
            if (code == orderitem.getCode()) {

                int amount = orderitem.getAmount();

                if (amount > 1) {

                    double price = orderitem.getPrice();
                    price = price / amount;
                    amount = amount - 1;
                    price = price * amount;
                    orderitem.setAmount(amount);
                    orderitem.setPrice(price);
                } else {
                    order.remove(orderitem);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void placeOrder(int tableNr) {

        totalOrder.add(new Product(12345, "Bestelling", 0, 0));
        orderDao.insertOrder(tableNr, order);
        for (Product orderItem : order) {

            totalOrder.add(orderItem);
            totalPrice = totalPrice + (orderItem.getPrice());
        }
    }

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void clearOrder() {
        order.clear();
    }

    @Override
    public void clearTotalOrder() {
        totalOrder.clear();
    }

    @Override
    public void checkOut(int tableNr) {
        orderDao.updateCheckOut(tableNr);
    }

    @Override
    public void addProduct(long code, int tableNr) {

        order = productDao.getProduct(code, tableNr);
    }

    @Override
    public boolean addProductCheck(int tableNr) {
        if (productDao.addProductCheck(tableNr)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Product> getOrder() {
        return order;
    }
}
