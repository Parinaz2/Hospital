public class Room {
    private String roomId;
    private String roomType;
    private boolean isAvailaible;

    // Constructor
    public Room(String roomId, String roomType, boolean isAvailaible) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.isAvailaible = isAvailaible;
    }

    // Getter and Setter Methods
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailaible() {
        return isAvailaible;
    }

    public void setAvailaible(boolean isAvailaible) {
        this.isAvailaible = isAvailaible;
    }

    // Method to display room status
    public void displayRoomStatus() {
        System.out.println("Room ID: " + roomId);
        System.out.println("Room Type: " + roomType);
        System.out.println("Occupied: " + (isAvailaible ? "Yes" : "No"));
    }
//    private int roomNumber;
//    private boolean isAvailaible;
//    public Room(int roomNumber) {
//        this.roomNumber = roomNumber;
//        //اول اتاق خالیه
//        this.isAvailaible = false;
//
//    }
//    //چون شماره اتاق تغییر نمیکند و موقع شی ساختن ثابته پس نیازی به تغییر شماره اتاق بعد از شی ساختن نیس و setter نیست
//    public int getRoomNumber() {
//        return roomNumber;
//    }
//    //قراره که تغییر بکنه ولی چون از متد استفاده کردیم نیازی نیست به setter
//    public boolean isAvailaible() {
//        return isAvailaible;
//    }
//    // متد برای اشغال کردن اتاق
//    public void full() {
//        isAvailaible = true;
//    }
//
//    // متد برای آزاد کردن اتاق
//    public void empty() {
//        isAvailaible = false;
//    }
}
