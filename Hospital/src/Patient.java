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
    public void addToDatabase(){

        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "INSERT INTO patients (name, roomNumber, bimari, age) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, getName());
            preparedStatement.setInt(2, roomNumber);
            preparedStatement.setString(3, bimari);
            preparedStatement.setInt(4, age);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Patient added successfully!");
            }
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
