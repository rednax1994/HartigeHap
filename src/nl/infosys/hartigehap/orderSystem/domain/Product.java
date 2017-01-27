/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.orderSystem.domain;

import java.util.ArrayList;

/**
 *
 * @author Richard de Jongh
 */
public class Product implements ImmutableProduct {

    private long code;
    private double price;
    private String name;
    private int amount;

    private ArrayList<Product> order;

    public Product(long productCode, String productName, int productAmount, double productPrice) {
        code = productCode;
        name = productName;
        amount = productAmount;
        price = productPrice;

        order = new ArrayList<>();
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setAmount(int productAmount) {
        amount = productAmount;
    }

    @Override
    public void setPrice(double productPrice) {
        price = productPrice;
    }
}
    


