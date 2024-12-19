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
