import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public void cancelOrder(String orderId) {
        orders.removeIf(order -> order.getOrderId().equals(orderId));
    }

    public List<Order> getOrderHistory(String userId) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}
