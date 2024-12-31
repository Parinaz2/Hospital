import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Patient extends Person {
    private String bimari;
    private int roomNumber;
    private int age;
    public Patient(String id,String name,String bimari,int roomNumber , int age){
        super(name,id);
        this.bimari=bimari;
        this.roomNumber=roomNumber;
        this.age=age;
    }
    public Patient(String name,String bimari,int roomNumber , int age){
        super(name);
        this.bimari=bimari;
        this.roomNumber=roomNumber;
        this.age=age;
    }

    public Patient(String name, int age, int roomNumber, String bimari) {
        super(name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getBimari(){
        return bimari;
    }
    public void setBimari(String bimari){
        this.bimari=bimari;
    }
    public int getRoomNumber(){
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber){
        this.roomNumber=roomNumber;
    }

    public void saveToDatabase(Connection conn) {
        String sql = "INSERT INTO patients (name, age, roomNumber, bimari) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.name);
            stmt.setInt(2, this.age);
            stmt.setInt(3, this.roomNumber);
            stmt.setString(4, this.bimari);
            stmt.executeUpdate();
            System.out.println("Patient added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("Patient Name: " + name + "\n"+
                "ID: " + id +"\n"+
                "Room: " + roomNumber + "\n"+
                "Bimari: " + bimari);
    }
}
