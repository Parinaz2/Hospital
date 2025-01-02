import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Staff extends Person {
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
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getDepartment() {
        return department;
    }
    public void saveToDatabase(Connection conn) {
        String sql = "INSERT INTO staff (name, role, department) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.name);
            stmt.setString(2, this.role);
            stmt.setString(3, this.department);
            stmt.executeUpdate();
            System.out.println("Staff added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    @Override
    public void displayDetails() {
        System.out.println("Staff Name: " + name + ", ID: " + id + ", Role: " + role +", Department: " +department) ;
    }
}
