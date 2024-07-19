import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CanteenManagementConsole {
    private static UserManager userManager = new UserManager();
    private static MenuManager menuManager = new MenuManager();
    private static OrderManager orderManager = new OrderManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Adding default admin user
        userManager.addUser(new User("admin", "Administrator", "admin"));

        while (true) {
            System.out.println("1. User Management");
            System.out.println("2. Menu Management");
            System.out.println("3. Order Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    handleUserManagement(scanner);
                    break;
                case 2:
                    handleMenuManagement(scanner);
                    break;
                case 3:
                    handleOrderManagement(scanner);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleUserManagement(Scanner scanner) {
        System.out.println("1. Add User");
        System.out.println("2. Delete User");
        System.out.println("3. View Users");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter user ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter user name: ");
                String name = scanner.nextLine();
                System.out.print("Enter user role (admin/student/staff): ");
                String role = scanner.nextLine();
                userManager.addUser(new User(id, name, role));
                break;
            case 2:
                System.out.print("Enter user ID to delete: ");
                String userId = scanner.nextLine();
                userManager.deleteUser(userId);
                break;
            case 3:
                List<User> users = userManager.getUsers();
                for (User user : users) {
                    System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Role: " + user.getRole());
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void handleMenuManagement(Scanner scanner) {
        System.out.println("1. Add Menu Item");
        System.out.println("2. Delete Menu Item");
        System.out.println("3. View Menu");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter item ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter item name: ");
                String name = scanner.nextLine();
                System.out.print("Enter item price: ");
                double price = scanner.nextDouble();
                menuManager.addMenuItem(new MenuItem(id, name, price));
                break;
            case 2:
                System.out.print("Enter item ID to delete: ");
                String itemId = scanner.nextLine();
                menuManager.deleteMenuItem(itemId);
                break;
            case 3:
                List<MenuItem> menu = menuManager.getMenu();
                for (MenuItem item : menu) {
                    System.out.println("ID: " + item.getId() + ", Name: " + item.getName() + ", Price: " + item.getPrice());
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void handleOrderManagement(Scanner scanner) {
        System.out.println("1. Place Order");
        System.out.println("2. Cancel Order");
        System.out.println("3. View Order History");
        System.out.println("4. View All Orders");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter user ID: ");
                String userId = scanner.nextLine();
                List<MenuItem> items = new ArrayList<>();
                while (true) {
                    System.out.print("Enter item ID to add to order (or 'done' to finish): ");
                    String itemId = scanner.nextLine();
                    if (itemId.equals("done")) {
                        break;
                    }
                    MenuItem item = menuManager.getMenu().stream()
                        .filter(menuItem -> menuItem.getId().equals(itemId))
                        .findFirst()
                        .orElse(null);
                    if (item != null) {
                        items.add(item);
                    } else {
                        System.out.println("Invalid item ID. Please try again.");
                    }
                }
                String orderId = "ORD" + (orderManager.getAllOrders().size() + 1);
                Order order = new Order(orderId, userId, items);
                orderManager.placeOrder(order);
                break;
            case 2:
                System.out.print("Enter order ID to cancel: ");
                String orderIdToCancel = scanner.nextLine();
                orderManager.cancelOrder(orderIdToCancel);
                break;
            case 3:
                System.out.print("Enter user ID to view order history: ");
                String userIdToView = scanner.nextLine();
                List<Order> userOrders = orderManager.getOrderHistory(userIdToView);
                for (Order userOrder : userOrders) {
                    System.out.println("Order ID: " + userOrder.getOrderId() + ", Total Price: " + userOrder.getTotalPrice());
                    for (MenuItem item : userOrder.getItems()) {
                        System.out.println("  Item ID: " + item.getId() + ", Name: " + item.getName() + ", Price: " + item.getPrice());
                    }
                }
                break;
            case 4:
                List<Order> allOrders = orderManager.getAllOrders();
                for (Order allOrder : allOrders) {
                    System.out.println("Order ID: " + allOrder.getOrderId() + ", User ID: " + allOrder.getUserId() + ", Total Price: " + allOrder.getTotalPrice());
                    for (MenuItem item : allOrder.getItems()) {
                        System.out.println("  Item ID: " + item.getId() + ", Name: " + item.getName() + ", Price: " + item.getPrice());
                    }
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
