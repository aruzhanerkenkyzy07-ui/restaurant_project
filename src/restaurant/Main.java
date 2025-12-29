package restaurant;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        MenuItem pizza = new MenuItem(1, "Pizza", 2500);
        MenuItem burger = new MenuItem(2, "Burger", 1800);

        Order order1 = new Order(101, pizza, 2);
        Order order2 = new Order(102, burger, 3);
        Order order3 = new Order(103, pizza, 1);

        Restaurant restaurant = new Restaurant(1, "Ocean");
        restaurant.addOrder(order1);
        restaurant.addOrder(order2);
        restaurant.addOrder(order3);

        System.out.println("=== ALL ORDERS ===");
        restaurant.getOrders().forEach(System.out::println);

        System.out.println("\n=== FILTER (total > 5000) ===");
        restaurant.getOrders().stream()
                .filter(o -> o.getTotalPrice() > 5000)
                .forEach(System.out::println);

        System.out.println("\n=== SORT BY TOTAL PRICE ===");
        List<Order> sorted = restaurant.getOrders().stream()
                .sorted(Comparator.comparing(Order::getTotalPrice))
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);

        System.out.println("\n=== SEARCH ORDER ID 102 ===");
        Order found = restaurant.getOrders().stream()
                .filter(o -> o.getId() == 102)
                .findFirst()
                .orElse(null);

        System.out.println(found != null ? found : "Order not found");
    }
}
