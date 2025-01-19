import java.sql.*;

public class Patient extends Person {
    private  int admissionHour;
    private String bimari;
    private int roomNumber;
    private int age;
    private int national_id;
    public Patient(String id,String name,int admissionHour){
        super(name,id);
        this.admissionHour=admissionHour;
    }
    public Patient(String id,String name,String bimari,int roomNumber , int age){
        super(name,id);
        this.bimari=bimari;
        this.roomNumber=roomNumber;
        this.age=age;
    }
    public Patient(String name,String bimari,int roomNumber , int age , int national_id){
        super(name);
        this.bimari=bimari;
        this.roomNumber=roomNumber;
        this.age=age;
        this.national_id=national_id;
    }

    public int getAdmissionHour() {
        return admissionHour;
    }

    public Patient(String name) {
        super(name);
    }
    public int getNational_id(){
        return national_id;
    }
    public void setNational_id(int national_id){
        this.national_id=national_id;
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

    public Patient() {
        super();
    }
   public  void saveToDatabase(Connection conn) {
        String sql = "INSERT INTO patients (name, age, roomNumber, bimari , national_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.name);
            stmt.setInt(2, this.age);
            stmt.setInt(3, this.roomNumber);
            stmt.setString(4, this.bimari);
            stmt.setInt(5, this.national_id);
            stmt.executeUpdate();
            System.out.println("Patient added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deletePatientByNationalId(String nationalId) {
        String sql = "DELETE FROM patients WHERE national_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // مقداردهی به پارامتر کد ملی
            preparedStatement.setString(1, nationalId);

            // اجرای دستور DELETE
            int rowsAffected = preparedStatement.executeUpdate();

            // بررسی حذف
            if (rowsAffected > 0) {
                System.out.println("Patient with National ID " + nationalId + " has been deleted.");
                return true;
            } else {
                System.out.println("No patient found with National ID " + nationalId + ".");
                return false;
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }
    public static Patient searchPatientByNationalId(String nationalId) {
        String query = "SELECT * FROM patients WHERE national_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nationalId);
           ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int roomNumber = rs.getInt("roomNumber");
                String bimari = rs.getString("bimari");
                int nationalIdFromDb = rs.getInt("national_id");
                return new Patient( name, bimari ,  roomNumber , age, nationalIdFromDb);
            } else {
                System.out.println("not found");
                return null; // No patient found with the given National ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        }
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Bimari: " + bimari +
                ", National ID: " + national_id + ", Room Number: " + roomNumber;
    }
    @Override
    public void displayDetails() {
        System.out.println("Patient Name: " + name + "\n"+
                "NationalId: " + national_id +"\n"+
                "Room: " + roomNumber + "\n"+
                "Bimari: " + bimari);
    }
}
