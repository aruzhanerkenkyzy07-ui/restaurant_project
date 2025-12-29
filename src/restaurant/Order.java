package restaurant;

public class Order extends Entity {

    private MenuItem item;
    private int quantity;

    public Order(int id, MenuItem item, int quantity) {
        super(id);
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }

    @Override
    public String getInfo() {
        return item.getInfo() + ", quantity: " + quantity;
    }

    @Override
    public String toString() {
        return "Order{id=" + id +
                ", item=" + item.getInfo() +
                ", quantity=" + quantity +
                ", total=" + getTotalPrice() + "}";
    }
}
