package db;

import java.sql.*;

public class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/vendingdb";
    private static final String USER = "root";
    private static final String PASS = "tamanna@2006";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Get napkin stock
    public static int getNapkinStock() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT stock FROM napkins WHERE id=1")) {
            if (rs.next()) return rs.getInt("stock");
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    // Update napkin stock
    public static void updateNapkinStock(int newStock) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE napkins SET stock=? WHERE id=1")) {
            ps.setInt(1, newStock);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
