package restaurant;

public class MenuItem extends Entity {

    private String name;
    private double price;

    public MenuItem(int id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String getInfo() {
        return name + " - " + price;
    }

    @Override
    public String toString() {
        return "MenuItem{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}
