package restaurant;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends Entity {

    private String name;
    private List<Order> orders = new ArrayList<>();

    public Restaurant(int id, String name) {
        super(id);
        this.name = name;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String getInfo() {
        return "Restaurant: " + name;
    }

    @Override
    public String toString() {
        return "Restaurant{id=" + id + ", name='" + name + "', orders=" + orders.size() + "}";
    }
}
