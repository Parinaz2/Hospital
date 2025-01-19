import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomId;
    private String roomNumber;
    private static boolean isAvailaible;

    // Constructor
    public Room(int roomId, String roomNumber, boolean isAvailaible) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.isAvailaible = isAvailaible;
    }

    public Room(String roomNumber, boolean isAvailable) {
        this.roomNumber=roomNumber;
        this.isAvailaible=isAvailable;
    }

    // Getter and Setter Methods
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isAvailaible() {
        return isAvailaible;
    }

    public void setAvailaible(boolean isAvailaible) {
        this.isAvailaible = isAvailaible;
    }
    public static Room searchRoomByRoomNumber(int roomNumber) {
        String query = "SELECT * FROM rooms WHERE room_number = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, roomNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String roomNum = rs.getString("room_number");
                boolean isAvailable = rs.getInt("is_available") == 1;

                return new Room(roomNum, isAvailable);
            } else {
                System.out.println("Room not found");
                return null; // No room found with the given room number
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveToDatabase(Connection conn) throws SQLException {
        String sql = "INSERT INTO rooms (room_number, is_available) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomNumber);
            stmt.setBoolean(2, isAvailaible);
            stmt.executeUpdate();
        }
    }

    public void updateRoomInDatabase(Connection conn) throws SQLException {
        String sql = "UPDATE rooms SET is_available = ? WHERE room_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, isAvailaible);
            stmt.setString(2, roomNumber);
            stmt.executeUpdate();
        }
    }

    public static boolean assignRoomToPatient(String patientId, String roomNumber, Connection conn) throws SQLException {
        // بررسی وضعیت اتاق
        if (!isRoomAvailable(roomNumber, conn)) {
            JOptionPane.showMessageDialog(null, "The room is already occupied.", "Room Occupied", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            // شروع تراکنش
            conn.setAutoCommit(false);

            // به‌روزرسانی جدول بیماران
            String updatePatientSql = "UPDATE patients SET room_number = ? WHERE patient_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updatePatientSql)) {
                pstmt.setString(1, roomNumber); // شماره اتاق
                pstmt.setString(2, patientId); // شناسه بیمار
                pstmt.executeUpdate();
            }

            // به‌روزرسانی جدول اتاق‌ها
            String updateRoomSql = "UPDATE rooms SET available = 0 WHERE room_number = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateRoomSql)) {
                pstmt.setString(1, roomNumber); // شماره اتاق
                pstmt.executeUpdate();
            }

            // تایید تراکنش
            conn.commit();
            JOptionPane.showMessageDialog(null, "Room " + roomNumber + " has been successfully assigned to the patient.", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (SQLException e) {
            conn.rollback(); // بازگشت تراکنش در صورت خطا
            JOptionPane.showMessageDialog(null, "Error assigning room: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;

        } finally {
            conn.setAutoCommit(true); // بازگرداندن تنظیمات پیش‌فرض
        }
    }



    public static List<String> getFullRooms(Connection conn) throws SQLException {
        List<String> fullRooms = new ArrayList<>();

        String sql = "SELECT room_number FROM rooms WHERE available = 0";  // اتاق‌هایی که پر هستند
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // گرفتن شماره اتاق از دیتابیس و افزودن آن به لیست (اتاق‌ها به صورت String هستند)
                String roomNumber = rs.getString("room_number");  // از getString به جای getInt استفاده می‌کنیم
                fullRooms.add(roomNumber);
            }
        }

        return fullRooms;
    }
    public static boolean isRoomAvailable(String roomNumber, Connection conn) throws SQLException {
        String sql = "SELECT available FROM rooms WHERE room_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomNumber); // مقدار شماره اتاق
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("available") == 1; // آزاد بودن اتاق
                }
            }
        }
        return false; // اتاق موجود نیست یا اشغال‌شده
    }

    public static void displayOccupiedRooms(Connection conn) throws SQLException {
        String sql = "SELECT room_number FROM rooms WHERE available = 0";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            StringBuilder occupiedRooms = new StringBuilder("Occupied Rooms:\n");
            while (rs.next()) {
                occupiedRooms.append(rs.getString("room_number")).append("\n");
            }

            JOptionPane.showMessageDialog(null, occupiedRooms.toString(), "Occupied Rooms", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}