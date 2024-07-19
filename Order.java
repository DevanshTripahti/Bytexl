import java.util.List;

public class Order {
    private String orderId;
    private String userId;
    private List<MenuItem> items;
    private double totalPrice;

    public Order(String orderId, String userId, List<MenuItem> items) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.totalPrice = calculateTotalPrice(items);
    }

    private double calculateTotalPrice(List<MenuItem> items) {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
