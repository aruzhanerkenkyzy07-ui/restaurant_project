package restaurant;

import java.sql.*;

public class MainDB {
    public static void main(String[] args) {

        try (Connection conn = DB.connect()) {

            // 1) CREATE TABLES (если вдруг их нет)
            String createMenuItem = """
                    CREATE TABLE IF NOT EXISTS menu_item (
                        id INT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        price NUMERIC(10,2) NOT NULL
                    );
                    """;

            String createOrders = """
                    CREATE TABLE IF NOT EXISTS orders (
                        id INT PRIMARY KEY,
                        menu_item_id INT NOT NULL REFERENCES menu_item(id),
                        quantity INT NOT NULL CHECK (quantity > 0)
                    );
                    """;

            Statement st = conn.createStatement();
            st.execute(createMenuItem);
            st.execute(createOrders);

            // 2) WRITE (INSERT)
            String insertItem = "INSERT INTO menu_item (id, name, price) VALUES (?, ?, ?) ON CONFLICT (id) DO NOTHING";
            PreparedStatement ps1 = conn.prepareStatement(insertItem);
            ps1.setInt(1, 1);
            ps1.setString(2, "Pizza");
            ps1.setDouble(3, 2500);
            ps1.executeUpdate();

            ps1.setInt(1, 2);
            ps1.setString(2, "Burger");
            ps1.setDouble(3, 1800);
            ps1.executeUpdate();

            String insertOrder = "INSERT INTO orders (id, menu_item_id, quantity) VALUES (?, ?, ?) ON CONFLICT (id) DO NOTHING";
            PreparedStatement ps2 = conn.prepareStatement(insertOrder);
            ps2.setInt(1, 101);
            ps2.setInt(2, 1);
            ps2.setInt(3, 2);
            ps2.executeUpdate();

            ps2.setInt(1, 102);
            ps2.setInt(2, 2);
            ps2.setInt(3, 3);
            ps2.executeUpdate();

            // 3) READ (SELECT)
            System.out.println("\n=== MENU ITEMS ===");
            ResultSet rs1 = st.executeQuery("SELECT * FROM menu_item ORDER BY id");
            while (rs1.next()) {
                System.out.println(rs1.getInt("id") + " | " +
                        rs1.getString("name") + " | " +
                        rs1.getBigDecimal("price"));
            }

            System.out.println("\n=== ORDERS (JOIN) ===");
            ResultSet rs2 = st.executeQuery("""
                    SELECT o.id, m.name, o.quantity, (m.price * o.quantity) AS total
                    FROM orders o
                    JOIN menu_item m ON m.id = o.menu_item_id
                    ORDER BY o.id
                    """);

            while (rs2.next()) {
                System.out.println("Order " + rs2.getInt("id") +
                        " | " + rs2.getString("name") +
                        " | qty=" + rs2.getInt("quantity") +
                        " | total=" + rs2.getBigDecimal("total"));
            }

            // 4) UPDATE
            String updatePrice = "UPDATE menu_item SET price = ? WHERE id = ?";
            PreparedStatement ps3 = conn.prepareStatement(updatePrice);
            ps3.setBigDecimal(1, new java.math.BigDecimal("2700.00"));
            ps3.setInt(2, 1);
            ps3.executeUpdate();

            String updateQty = "UPDATE orders SET quantity = ? WHERE id = ?";
            PreparedStatement ps4 = conn.prepareStatement(updateQty);
            ps4.setInt(1, 5);
            ps4.setInt(2, 102);
            ps4.executeUpdate();

            System.out.println("\n=== AFTER UPDATE ===");
            ResultSet rs3 = st.executeQuery("""
                    SELECT o.id, m.name, o.quantity, (m.price * o.quantity) AS total
                    FROM orders o
                    JOIN menu_item m ON m.id = o.menu_item_id
                    ORDER BY o.id
                    """);
            while (rs3.next()) {
                System.out.println("Order " + rs3.getInt("id") +
                        " | " + rs3.getString("name") +
                        " | qty=" + rs3.getInt("quantity") +
                        " | total=" + rs3.getBigDecimal("total"));
            }

            // 5) DELETE
            String deleteOrder = "DELETE FROM orders WHERE id = ?";
            PreparedStatement ps5 = conn.prepareStatement(deleteOrder);
            ps5.setInt(1, 101);
            ps5.executeUpdate();

            System.out.println("\n=== AFTER DELETE ===");
            ResultSet rs4 = st.executeQuery("SELECT * FROM orders ORDER BY id");
            while (rs4.next()) {
                System.out.println("Order " + rs4.getInt("id") +
                        " | item_id=" + rs4.getInt("menu_item_id") +
                        " | qty=" + rs4.getInt("quantity"));
            }

            System.out.println("\nCRUD completed successfully ✅");

        } catch (Exception e) {
            System.out.println("ERROR ❌ " + e.getMessage());
        }
    }
}
