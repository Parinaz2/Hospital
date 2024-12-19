public class Room {
    private int roomNumber;
    private boolean isAvailaible;
    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        //اول اتاق خالیه
        this.isAvailaible = false;

    }
    //چون شماره اتاق تغییر نمیکند و موقع شی ساختن ثابته پس نیازی به تغییر شماره اتاق بعد از شی ساختن نیس و setter نیست
    public int getRoomNumber() {
        return roomNumber;
    }
    //قراره که تغییر بکنه ولی چون از متد استفاده کردیم نیازی نیست به setter
    public boolean isAvailaible() {
        return isAvailaible;
    }
    // متد برای اشغال کردن اتاق
    public void full() {
        isAvailaible = true;
    }

    // متد برای آزاد کردن اتاق
    public void empty() {
        isAvailaible = false;
    }
}
