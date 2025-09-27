package db;

import java.sql.*;

public class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/vendingdb";
    private static final String USER = "root";
    private static final String PASS = "tamanna@2006";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ✅ Napkin methods
    public static int getNapkinStock() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT stock FROM napkins WHERE id=1")) {
            if (rs.next()) return rs.getInt("stock");
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public static void updateNapkinStock(int newStock) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE napkins SET stock=? WHERE id=1")) {
            ps.setInt(1, newStock);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // ✅ Coin methods
    public static int getCoinQuantity(int denom) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT quantity FROM coins WHERE denomination=?")) {
            ps.setInt(1, denom);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("quantity");
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public static void updateCoinQuantity(int denom, int qty) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE coins SET quantity=? WHERE denomination=?")) {
            ps.setInt(1, qty);
            ps.setInt(2, denom);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // ✅ Transaction log
    public static void insertTransaction(int napkinsSold, int amount) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO transactions (napkins_sold, amount) VALUES (?, ?)")) {
            ps.setInt(1, napkinsSold);
            ps.setInt(2, amount);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // ✅ Report queries
    public static int getTodaySales() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT SUM(napkins_sold) FROM transactions WHERE DATE(timestamp)=CURDATE()")) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public static int getTotalSales() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT SUM(napkins_sold) FROM transactions")) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public static int getTodayRevenue() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT SUM(amount) FROM transactions WHERE DATE(timestamp)=CURDATE()")) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public static int getTotalRevenue() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT SUM(amount) FROM transactions")) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    // ✅ Refill log
    public static void logNapkinRefill() {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO refill_log (napkin_refill_time) VALUES (NOW())")) {
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void logCoinRefill() {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO refill_log (coin_refill_time) VALUES (NOW())")) {
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}