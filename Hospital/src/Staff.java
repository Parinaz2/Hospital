public class Staff extends Person {
    private String role;
    private String department;
    public Staff(String id, String name, String role,String department) {
        super(name, id);
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

    public void setDepartment(String department) {
        this.department = department;
    }
    @Override
    public void displayDetails() {
        System.out.println("Staff Name: " + name + ", ID: " + id + ", Role: " + role +", Department: " +department) ;
    }
}
