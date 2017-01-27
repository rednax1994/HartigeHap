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
public interface ImmutableProduct {

    public long getCode();

    public double getPrice();

    public int getAmount();

    public String getName();

    public void setAmount(int productAmount);

    public void setPrice(double productPrice);

}
