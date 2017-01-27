/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.kitchenSystem.domain;

/**
 *
 * @author Infosys
 */
public class Employee {

    private int employeeId;
    private String firstName;
    private String dishName;

    public Employee() {

    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
