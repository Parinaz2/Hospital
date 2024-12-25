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
    @Override
    public void displayDetails() {
        System.out.println("Patient Name: " + name + ", ID: " + id + ", Room: " + roomNumber + ", Bimari: " + bimari);
    }
}
