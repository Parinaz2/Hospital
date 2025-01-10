import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
public class Room {
    private int roomId;
    private String roomNumber;
    private boolean isAvailaible;

    // Constructor
    public Room(int roomId, String roomNumber, boolean isAvailaible) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.isAvailaible = isAvailaible;
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

    public static void addRoom(String roomNumber) throws SQLException {
        String sql = "INSERT INTO rooms (room_number, is_available) VALUES (?, TRUE)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomNumber);
            pstmt.executeUpdate();
            System.out.println("Room added: " + roomNumber);
        }
    }

    // بررسی وضعیت در دسترس بودن اتاق

        public static boolean isRoomAvailable(String roomNumber) throws SQLException {
            String sql = "SELECT is_available FROM rooms WHERE room_id = ?";
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, roomNumber);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getBoolean("is_available");
                    }
                }
            }
            return false;
        }

        public static void markRoomAsOccupied(String roomNumber) throws SQLException {
            String sql = "UPDATE rooms SET is_available = FALSE WHERE room_id = ?";
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, roomNumber);
                pstmt.executeUpdate();
                System.out.println("Room " + roomNumber + " is now occupied.");
            }
        }


    // Method to display room status
    public void displayRoomStatus() {
        System.out.println("Room ID: " + roomId);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Occupied: " + (isAvailaible ? "Yes" : "No"));
    }
    // متدی برای تغییر وضعیت اتاق به "اشغال"
    public void occupyRoom() {
        this.isAvailaible = false;
    }

    // متدی برای آزاد کردن اتاق
    public void freeRoom() {
        this.isAvailaible = true;
    }

    // نمایش وضعیت اتاق
    public String getRoomStatus() {
        return isAvailaible ? "Available" : "Occupied";
    }
}