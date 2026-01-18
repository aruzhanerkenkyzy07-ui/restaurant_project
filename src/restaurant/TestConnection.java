package restaurant;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = DB.connect()) {
            System.out.println("CONNECTED ✅");
        } catch (Exception e) {
            System.out.println("ERROR ❌ " + e.getMessage());
        }
    }
}
