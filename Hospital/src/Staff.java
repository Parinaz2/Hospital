public class Staff extends Person {
    private String role;
    public Staff(String id, String name, String role) {
        super(name, id);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public void displayDetails() {
        System.out.println("Staff Name: " + name + ", ID: " + id + ", Role: " + role);
    }
}
