/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.orderSystem.domain;

/**
 *
 * @author Sander van Belleghem
 */
public interface ImmutableOrder {

    public int getTablenumber();

    public void setTablenumber(int tablenumber);

    public double getTotalprice();

    public void setTotalprice(double price);

    public String getOrderstate();

    public void setOrderstate(String orderstate);
}
