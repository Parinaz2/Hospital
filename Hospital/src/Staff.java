import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Staff extends Person {
    private int national_id;
    private String role;
    private String department;
    public Staff(String id, String name, String role,String department) {
        super(name, id);
        this.role = role;
        this.department=department;
    }
    public Staff(String name, String role,String department) {
        super(name);
        this.role = role;
        this.department=department;
    }
    public Staff(String name, String role,String department,int national_id) {
        super(name);
        this.role = role;
        this.department=department;
        this.national_id=national_id;
    }

    public Staff() {
        super();
    }

    public int getNational_id(){
        return national_id;
    }
    public void setNational_id(int national_id){
        this.national_id=national_id;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void saveToDatabase(Connection conn) {
        String sql = "INSERT INTO staff (name, role, department , national_id) VALUES (?, ?, ? ,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.name);
            stmt.setString(2, this.role);
            stmt.setString(3, this.department);
            stmt.setInt(4, this.national_id);
            stmt.executeUpdate();
            System.out.println("Staff added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Staff searchStaffByNationalId(String nationalId) {
        String query = "SELECT * FROM staff WHERE national_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nationalId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String role = rs.getString("role");
                String department = rs.getString("department");
                int nationalIdFromDb = rs.getInt("national_id");
                return new Staff( name, role ,  department, nationalIdFromDb);
            } else {
                System.out.println("not found");
                return null; // No patient found with the given National ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean deleteStaffByNationalId(String nationalId) {
        String sql = "DELETE FROM staff WHERE national_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // مقداردهی به پارامتر کد ملی
            preparedStatement.setString(1, nationalId);

            // اجرای دستور DELETE
            int rowsAffected = preparedStatement.executeUpdate();

            // بررسی حذف
            if (rowsAffected > 0) {
                System.out.println("Staff with National ID " + nationalId + " has been deleted.");
                return true;
            } else {
                System.out.println("No staff found with National ID " + nationalId + ".");
                return false;
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }
    public String toString() {
        return "Name: " + name + ", Role: " + role +", Department: " +department+
                ", National ID: " + national_id ;
    }
    @Override
    public void displayDetails() {
        System.out.println("Staff Name: " + name + ", ID: " + id + ", Role: " + role +", Department: " +department) ;
    }
}
