public class Doctor extends Person {
    private String role;
    public Doctor(String id, String name, String role){
        super(name,id);
        this.role=role;
    }


    @Override
    public void displayDetails() {
        System.out.println("Doctors Name: " + name + ", ID: " + id + ", Role: " + role ) ;
    }
}
