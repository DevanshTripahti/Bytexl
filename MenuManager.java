import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private List<MenuItem> menu = new ArrayList<>();

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public void deleteMenuItem(String itemId) {
        menu.removeIf(item -> item.getId().equals(itemId));
    }

    public List<MenuItem> getMenu() {
        return menu;
    }
}
