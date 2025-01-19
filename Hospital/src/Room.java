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