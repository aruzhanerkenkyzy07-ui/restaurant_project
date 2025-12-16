package restaurant;

public class Main {
    public static void main(String[] args) {

        Restaurant r1 = new Restaurant(1, "Ocean", "Seafood", 4.6);
        Restaurant r2 = new Restaurant(2, "Italiano", "Italian", 3.9);

        System.out.println(r1);
        System.out.println(r2);

        if (r1.getRating() > r2.getRating()) {
            System.out.println(r1.getName() + " has higher rating");
        } else {
            System.out.println(r2.getName() + " has higher rating");
        }

        System.out.println("Is " + r1.getName() + " popular? " + r1.isPopular());
    }
}
