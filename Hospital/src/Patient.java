public class Patient extends Person {
    private String bimari;
    private int roomNumber;
    public Patient(String id,String name,String bimari,int roomNumber){
        super(name,id);
        this.bimari=bimari;
        this.roomNumber=roomNumber;
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
