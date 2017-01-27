package nl.infosys.hartigehap.kitchenSystem.businesslogic;

import nl.infosys.hartigehap.kitchenSystem.databaseacces.OrderDAO;
import nl.infosys.hartigehap.kitchenSystem.domain.Employee;
import nl.infosys.hartigehap.kitchenSystem.domain.Order;
import nl.infosys.hartigehap.kitchenSystem.domain.OrderDetail;
import java.util.ArrayList;

/**
 *
 * @author Infosys
 */
public class OrderManager {

    private OrderDAO orderDataList;
    private OrderDAO orderDetails;

    public OrderManager() {
        orderDataList = new OrderDAO();
        orderDetails = new OrderDAO();
    }

    //method that returns an arraylist with Order objects
    public ArrayList<Order> getOrders() {
        return orderDataList.findAllOrders();
    }

    public ArrayList<OrderDetail> getOrderDetails(int orderId) {
        return orderDetails.findOrderDetails(orderId);
    }

    public ArrayList<Employee> getEmployeeList() {
        return orderDataList.findAllEmployees();
    }

    public ArrayList<Employee> getCoupledEmployeeList(int orderId) {
        return orderDataList.findAllCoupledEmployees(orderId);
    }

    /**
     *
     * @param employeeId
     * @param orderId
     * @param productId
     */
    public void updateCoupledOrderIdOp(int employeeId, int orderId, int productId) {
        orderDataList.updateCoupledOrders(employeeId, orderId, productId);
    }

    public void updateAcceptionStatus(int orderId, boolean status) {
        orderDataList.updateAcceptionStatus(orderId, status);
    }

    public void updateDeliveryStatus(int order) {
        orderDataList.updateDeliveryStatus(order);
    }
}
