import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
public class Room {
    private int roomId;
    private String roomType;
    private boolean isAvailaible;

    // Constructor
    public Room(int roomId, String roomType, boolean isAvailaible) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.isAvailaible = isAvailaible;
    }

    // Getter and Setter Methods
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailaible() {
        return isAvailaible;
    }

    public void setAvailaible(boolean isAvailaible) {
        this.isAvailaible = isAvailaible;
    }

    // Method to display room status
    public void displayRoomStatus() {
        System.out.println("Room ID: " + roomId);
        System.out.println("Room Type: " + roomType);
        System.out.println("Occupied: " + (isAvailaible ? "Yes" : "No"));
    }
    public void addToDatabase() {
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "INSERT INTO rooms (roomId, isAvailable) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            preparedStatement.setBoolean(2, isAvailaible);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Room added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}