package restaurant;

public class Main {

    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant(1, "Ocean", "Main Street 10", 4.5);

        MenuItem item1 = new MenuItem(1, "Pizza", 2500);
        MenuItem item2 = new MenuItem(2, "Burger", 1800);

        Order order1 = new Order(101, item1, 2);
        Order order2 = new Order(102, item2, 3);

        System.out.println(restaurant);
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(order1);
        System.out.println(order2);

        if (order1.getTotalPrice() > order2.getTotalPrice()) {
            System.out.println("Order 1 is more expensive");
        } else {
            System.out.println("Order 2 is more expensive");
        }
    }
}
