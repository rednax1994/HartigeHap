/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.infosys.hartigehap.kitchenSystem.domain;

/**
 *
 * @author Infosys
 */
public class OrderDetail {
    private int    ammount;
    private String name;
    private String description;
    private int    averageTime;
    private int    productId;
   
    public OrderDetail(){
        
    }

    public int getAmmount() {
        return ammount;
    }

    public String getName() {
        return name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDescription() {
        if(description != null) {
            return description;
        }
        return "Geen beschrijving";
    }


    public int getAverageTime() {
        return averageTime;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAverageTime(int averageTime) {
        this.averageTime = averageTime;
    } 
}