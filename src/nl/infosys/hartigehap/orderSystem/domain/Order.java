/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.orderSystem.domain;

import java.util.ArrayList;

/**
 * @author devc0n
 */
public class Order implements ImmutableOrder {

    private int tableNr;
    private double totalprice;
    private String state;

    public Order(int tablenumber) {
        tableNr = tablenumber;
    }

    @Override
    public int getTablenumber() {
        return tableNr;
    }

    @Override
    public void setTablenumber(int tablenumber) {
        tableNr = tablenumber;
    }

    @Override
    public double getTotalprice() {
        return totalprice;
    }

    @Override
    public void setTotalprice(double price) {
        totalprice = price;
    }

    @Override
    public String getOrderstate() {
        return state;
    }

    @Override
    public void setOrderstate(String orderstate) {
        state = orderstate;
    }

}
