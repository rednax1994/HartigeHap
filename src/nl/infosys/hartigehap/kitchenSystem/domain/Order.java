package nl.infosys.hartigehap.kitchenSystem.domain;

import java.sql.Time;
//import javafx.scene.control.ComboBox;

/**
 *
 * @author Infosys
 */
public class Order {

    private int orderId;
    private int tableNumber;
    private Time timeStamp;
    private String status;
    private boolean accepted;

    public Order() {

    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public Time getTimeStamp() {
        return timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public String getAccepted() {
        if (!accepted) {
            return "Nee ";
        } else {
            return "Ja";
        }

    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setTimeStamp(Time timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public JComboBox getComboBox() {
//        JComboBox comboBox = new JComboBox();
//        comboBox.addItem("Geleverd");
//        comboBox.addItem("In Verwerking");
//        comboBox.addItem("Gereed");
//        return comboBox;
//    }
}
