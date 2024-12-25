import java.util.ArrayList;
import java.util.List;

public class HospitalManager {
    private List<Patient> patients =new ArrayList<>();
    private List<Staff> staffMember = new ArrayList<>();
    //اضافه کردن بیمار
    public void addPatient(Patient patient){
        patients.add(patient);
    }
    //حذف بیمار
    public void removePatient(String codemelli){
        for (Patient patient:patients){
            if (patient.id == codemelli){
                patients.remove(patient);
                return;
            }
        }
        return;
    }
}
//class HospitalManagement {
//    private Connection connection; // اتصال به پایگاه داده
//
//    public HospitalManagement(Connection connection) {
//        this.connection = connection;
//    }
//
//    // اضافه کردن بیمار به پایگاه داده
//    public void addPatient(String name, String id, String roomNumber, String disease) throws SQLException, InvalidInputException {
//        // بررسی وضعیت اتاق
//        if (!isRoomAvailable(roomNumber)) {
//            throw new InvalidInputException("اتاق در دسترس نیست.");
//        }
//
//        // اضافه کردن بیمار
//        String query = "INSERT INTO patients (id, name, room, disease) VALUES (?, ?, ?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setString(1, id);
//            stmt.setString(2, name);
//            stmt.setString(3, roomNumber);
//            stmt.setString(4, disease);
//            stmt.executeUpdate();
//        }
//
//        // به‌روزرسانی وضعیت اتاق
//        markRoomAsOccupied(roomNumber, true);
//        logAction("بیمار با شناسه " + id + " اضافه شد.");
//    }
//
//    // حذف بیمار از پایگاه داده
//    public void removePatient(String id) throws SQLException, InvalidInputException {
//        // یافتن بیمار و اتاق مرتبط
//        String roomNumber = null;
//        String queryFind = "SELECT room FROM patients WHERE id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(queryFind)) {
//            stmt.setString(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                roomNumber = rs.getString("room");
//            } else {
//                throw new InvalidInputException("بیمار یافت نشد.");
//            }
//        }
//
//        // حذف بیمار
//        String queryDelete = "DELETE FROM patients WHERE id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(queryDelete)) {
//            stmt.setString(1, id);
//            stmt.executeUpdate();
//        }
//
//        // به‌روزرسانی وضعیت اتاق
//        markRoomAsOccupied(roomNumber, false);
//        logAction("بیمار با شناسه " + id + " حذف شد.");
//    }
//
//    // افزودن اتاق جدید به پایگاه داده
//    public void addRoom(String roomNumber) throws SQLException {
//        String query = "INSERT INTO rooms (room_number, is_occupied) VALUES (?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setString(1, roomNumber);
//            stmt.setBoolean(2, false);
//            stmt.executeUpdate();
//        }
//        logAction("اتاق با شماره " + roomNumber + " اضافه شد.");
//    }
//
//    // بررسی موجود بودن اتاق
//    private boolean isRoomAvailable(String roomNumber) throws SQLException {
//        String query = "SELECT is_occupied FROM rooms WHERE room_number = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setString(1, roomNumber);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return !rs.getBoolean("is_occupied");
//            } else {
//                throw new InvalidInputException("اتاق یافت نشد.");
//            }
//        }
//    }
//
//    // به‌روزرسانی وضعیت اشغال بودن اتاق
//    private void markRoomAsOccupied(String roomNumber, boolean isOccupied) throws SQLException {
//        String query = "UPDATE rooms SET is_occupied = ? WHERE room_number = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setBoolean(1, isOccupied);
//            stmt.setString(2, roomNumber);
//            stmt.executeUpdate();
//        }
//    }
//
//    // نمایش بیماران
//    public void displayPatients() throws SQLException {
//        String query = "SELECT id, name, room, disease FROM patients";
//        try (Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
//            while (rs.next()) {
//                System.out.println("شناسه: " + rs.getString("id") +
//                                   ", نام: " + rs.getString("name") +
//                                   ", اتاق: " + rs.getString("room") +
//                                   ", بیماری: " + rs.getString("disease"));
//            }
//        }
//    }
//
//    // نمایش اتاق‌ها
//    public void displayRooms() throws SQLException {
//        String query = "SELECT room_number, is_occupied FROM rooms";
//        try (Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
//            while (rs.next()) {
//                System.out.println("اتاق: " + rs.getString("room_number") +
//                                   ", اشغال شده: " + rs.getBoolean("is_occupied"));
//            }
//        }
//    }
//
//    // ثبت عملیات در گزارش
//    private void logAction(String action) {
//        try (FileWriter fw = new FileWriter("hospital_log.txt", true)) {
//            fw.write(action + "\n");
//        } catch (IOException e) {
//            System.out.println("خطا در ثبت گزارش: " + e.getMessage());
//        }
//    }
//}
//public class Main {
//    public static void main(String[] args) {
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "user", "password")) {
//            HospitalManagement hm = new HospitalManagement(connection);
//
//            // اضافه کردن اتاق
//            hm.addRoom("101");
//            hm.addRoom("102");
//
//            // اضافه کردن بیمار
//            hm.addPatient("علی", "12345", "101", "سرماخوردگی");
//
//            // نمایش بیماران
//            hm.displayPatients();
//
//            // نمایش اتاق‌ها
//            hm.displayRooms();
//        } catch (SQLException | InvalidInputException e) {
//            System.out.println("خطا: " + e.getMessage());
//        }
//    }
//}