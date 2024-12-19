public class Patient {
    private String codemelli;
    private String name;
    private String bimari;
    private int roomNumber;
    public Patient(String codemelli,String name,String bimari,int roomNumber){
        this.codemelli=codemelli;
        this.name=name;
        this.bimari=bimari;
        this.roomNumber=roomNumber;
    }
    public String getCodemelli(){
        return codemelli;
    }
    public void setCodemelli(String codemelli){
        this.codemelli=codemelli;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
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

}
