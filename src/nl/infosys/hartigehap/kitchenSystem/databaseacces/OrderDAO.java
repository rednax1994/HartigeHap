package nl.infosys.hartigehap.kitchenSystem.databaseacces;

import nl.infosys.hartigehap.kitchenSystem.datestore.DatabaseConnection;
import nl.infosys.hartigehap.kitchenSystem.domain.Employee;
import nl.infosys.hartigehap.kitchenSystem.domain.Order;
import nl.infosys.hartigehap.kitchenSystem.domain.OrderDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Infosys
 */
public class OrderDAO {

    private DatabaseConnection con;
    private OrderDetail orderDetails;
    private ArrayList<Order> orders;
    private ArrayList<OrderDetail> orderList;

    public OrderDAO() {
        con = new DatabaseConnection();
    }

    public ArrayList<Employee> findAllCoupledEmployees(int orderId) {
        con.getConnection();
        ArrayList<Employee> coupledEmployee = new ArrayList();
        ResultSet rs = con.ExecuteDB(
                "SELECT Naam, MedewerkerID, Voornaam "
                + "FROM Medewerker, BestelRegel, Product, Bestelling "
                + "WHERE Medewerker.MedewerkerID = BestelRegel.fk_MedewerkerID "
                + "AND BestelRegel.fk_BestellingID = Bestelling.BestellingID "
                + "AND BestelRegel.fk_ProductID = Product.ProductID "
                + "AND Medewerker.Functie = 'keukenmedewerker' "
                + "AND BestelRegel.LeverStatus =  'In Verwerking' "
                + "AND Bestelling.BestellingId = " + orderId + "; ");
        if (rs != null) {
            try {
                while (rs.next()) {
                    int employeeId = rs.getInt("MedewerkerID");
                    String dishName = rs.getString("Naam");
                    String firstName = rs.getString("Voornaam");
                    Employee employee = new Employee();
                    employee.setEmployeeId(employeeId);
                    employee.setFirstName(firstName);
                    employee.setDishName(dishName);
                    coupledEmployee.add(employee);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        con.closeConnection();
        return coupledEmployee;
    }

    public ArrayList<Employee> findAllEmployees() {
        con.getConnection();
        ArrayList<Employee> employeeList = new ArrayList();
        ResultSet rs = con.ExecuteDB("SELECT MedewerkerID, Voornaam  FROM `medewerker` WHERE Functie = 'keukenmedewerker';");
        if (rs != null) {
            try {
                while (rs.next()) {
                    int employeeId = rs.getInt("MedewerkerID");
                    String firstName = rs.getString("VoorNaam");
                    Employee employee = new Employee();
                    employee.setEmployeeId(employeeId);
                    employee.setFirstName(firstName);
                    employeeList.add(employee);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        con.closeConnection();
        return employeeList;
    }

    //method that returns an ArrayList that contains all orders that haven't been cooked yet
    public ArrayList<Order> findAllOrders() {
        con.getConnection();
        //create a new arraylist in variable orders
        orders = new ArrayList();
        //perform a select query
        ResultSet RS = con.ExecuteDB(""
                + "SELECT DISTINCT  Bestelling.BestellingID, Tafelnummer, TimeStamp, LeverStatus, accepted "
                + "FROM  bestelling, bestelregel, product, gerecht "
                + "WHERE bestelling.BestellingID = bestelregel.fk_BestellingID "
                + "AND  bestelregel.fk_ProductID = product.ProductID "
                + "AND  LeverStatus = 'In Verwerking' "
                + "AND  Categorie != 'Drankjes'  ORDER BY TIMESTAMP;");
        if (RS != null) {
            try {
                //loop through every row in the resultset and put the variables 
                //in an Order object
                while (RS.next()) {
                    int orderId = RS.getInt("BestellingID");
                    int tableNumber = RS.getInt("Tafelnummer");
                    Time timeStamp = RS.getTime("TimeStamp");
                    String deliveryStatus = RS.getString("LeverStatus");
                    boolean accepted = RS.getBoolean("accepted");
                    Order order = new Order();
                    order.setOrderId(orderId);
                    order.setTableNumber(tableNumber);
                    order.setTimeStamp(timeStamp);
                    order.setStatus(deliveryStatus);
                    order.setAccepted(accepted);
                    orders.add(order);
                }
                //show in console if something goes wrong
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        con.closeConnection();
        return orders;
    }

    public ArrayList<OrderDetail> findOrderDetails(int orderId) {
        con.getConnection();
        orderList = new ArrayList();
        ResultSet RS2 = con.ExecuteDB(
                "SELECT  Naam, Hoeveelheid, Beschrijving, GemiddeldeTijd, product.ProductID "
                + "FROM           bestelregel, product, gerecht, bestelling "
                + "WHERE          bestelregel.fk_ProductID = product.ProductID "
                + "AND            product.ProductID = gerecht.fk_ProductID "
                + "AND            bestelling.BestellingID = bestelregel.fk_BestellingID "
                + "AND            Categorie != 'Drankjes' "
                + "AND LeverStatus =  'In Verwerking' "
                + "AND            bestelling.BestellingID = " + orderId + ";");
        if (RS2 != null) {
            try {
                while (RS2.next()) {
                    /**
                     * Placing all orders into an Order object
                     */

                    int ammount = RS2.getInt("Hoeveelheid");
                    String name = RS2.getString("Naam");
                    String description = RS2.getString("Beschrijving");
                    int averageTime = RS2.getInt("GemiddeldeTijd");
                    int productId = RS2.getInt("ProductID");

                    //geef gegevens door aan entity layer
                    orderDetails = new OrderDetail();
                    orderDetails.setAmmount(ammount);
                    orderDetails.setName(name);
                    orderDetails.setDescription(description);
                    orderDetails.setAverageTime(averageTime);
                    orderDetails.setProductId(productId);
                    orderList.add(orderDetails);

                    //int Hoeveelheid, String Naam, String Beschrijving, int GemiddeldeTijd
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        con.closeConnection();
        return orderList;
    }

    public void updateCoupledOrders(int employeeId, int orderId, int productId) {
        con.getConnection();
        try {
            con.UpdateDB(""
                    + " UPDATE bestelregel SET fk_MedewerkerID = " + employeeId
                    + " WHERE fk_BestellingID = " + orderId
                    + " AND fk_ProductID = " + productId + ";");
        } catch (Exception e) {
            System.out.println(e);
        }
        con.closeConnection();
    }

    public void updateAcceptionStatus(int orderId, boolean status) {
        con.getConnection();
        try {
            con.UpdateDB(""
                    + "UPDATE bestelling SET accepted = " + status
                    + " WHERE BestellingID = " + orderId
            );
        } catch (Exception e) {
            System.out.println(e);
        }
        con.closeConnection();
    }
    /*
     * Even snel om alles terug op In Verwerking te zetten:
     * 
     UPDATE bestelregel, bestelling, product
     SET bestelregel.LeverStatus = 'In Verwerking'  
     WHERE bestelling.BestellingID = 1
     AND product.Categorie != 'Drankjes' 
     */

    public void updateDeliveryStatus(int orderId) {
        con.getConnection();
        try {
            con.UpdateDB(""
                    + " UPDATE bestelregel, bestelling, product "
                    + " SET bestelregel.LeverStatus = 'Gereed' "
                    + " WHERE bestelling.BestellingID = bestelregel.fk_BestellingID "
                    + " AND product.ProductID = bestelregel.fk_ProductID "
                    + " AND bestelling.BestellingID = " + orderId
                    + " AND product.Categorie != 'Drankjes' ;"
            );
        } catch (Exception e) {
            System.out.println(e);
        }
        con.closeConnection();
    }
}
