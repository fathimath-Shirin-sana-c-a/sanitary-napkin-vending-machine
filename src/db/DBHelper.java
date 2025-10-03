package db;

import java.sql.*;

public class DBHelper {
    private static final String URL  = "jdbc:mysql://localhost:3306/vendingdb";
    private static final String USER = "root";
    private static final String PASS = "tamanna@2006"; // keep your password

    static {
        // Ensure MySQL driver is loaded
        try { Class.forName("com.mysql.cj.jdbc.Driver"); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ===== Napkins =====
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

    public static void logNapkinRefill() {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO refill_log (napkin_refill_time) VALUES (NOW())")) {
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // ===== Coins =====
    public static int getCoinQuantity(int denom) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT quantity FROM coins WHERE denomination=?")) {
            ps.setInt(1, denom);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("quantity");
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    // set absolute quantity
    public static void updateCoinQuantity(int denom, int qty) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE coins SET quantity=? WHERE denomination=?")) {
            ps.setInt(1, qty);
            ps.setInt(2, denom);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // add/subtract quantity by delta (e.g., +1 on insert)
    public static void incrementCoinQuantity(int denom, int delta) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE coins SET quantity = quantity + ? WHERE denomination=?")) {
            ps.setInt(1, delta);
            ps.setInt(2, denom);
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

    // ===== Transactions / Reports =====
    public static void insertTransaction(int napkinsSold, int amount) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO transactions (napkins_sold, amount) VALUES (?, ?)")) {
            ps.setInt(1, napkinsSold);
            ps.setInt(2, amount);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static int getTodaySales() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT COALESCE(SUM(napkins_sold),0) FROM transactions WHERE DATE(timestamp)=CURDATE()")) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public static int getTodayRevenue() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT COALESCE(SUM(amount),0) FROM transactions WHERE DATE(timestamp)=CURDATE()")) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public static int getTotalSales() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COALESCE(SUM(napkins_sold),0) FROM transactions")) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public static int getTotalRevenue() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COALESCE(SUM(amount),0) FROM transactions")) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public static String getLastNapkinRefill() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT MAX(napkin_refill_time) FROM refill_log")) {
            if (rs.next()) {
                Timestamp t = rs.getTimestamp(1);
                return (t == null) ? "—" : t.toString();
            }
        } catch (Exception e) { e.printStackTrace(); }
        return "—";
    }

    public static String getLastCoinRefill() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT MAX(coin_refill_time) FROM refill_log")) {
            if (rs.next()) {
                Timestamp t = rs.getTimestamp(1);
                return (t == null) ? "—" : t.toString();
            }
        } catch (Exception e) { e.printStackTrace(); }
        return "—";
    }
}