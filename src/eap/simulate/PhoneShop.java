package eap.simulate;


import eap.abstractfactory.Camera;
import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.Phone;
import eap.abstractfactory.SmartPhone;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneShop {
    //Ενδεικτική λίστα με εταιρείες κατασκευής κινητών τηλεφώνων. Θεωρούμε ότι κατασκευάζουν κινητά όλων των κατηγοριών
    private final String[] manufacturers = new String[]{"Samsung","LG","Apple","Motorola"};
    //Ενδεικτική λίστα με ονόματα λειτουργικών συστημάτων κινητών
    private final String[] operatingSystems = new String[]{"Android","iOS","Tizen"};
    private final Random random = new Random();
    //Λίστα για την αποθήκευση των χαρακτηριστικών για κάθε τηλέφωνο που πρόκειται να παρχθεί
    private final List<Phone> phoneList = new ArrayList<>();

    public int numberOfPhones(){
        return phoneList.size();
    }

    //Όλα τα χαρακτηριστικά των τηλεφώνων πρέπει να παραχθούν με τυχαίο τρόπο
    public Phone createPhoneSpec(){
        Phone phone;
        
        // Υπολογίζουμε τυχαία specs, μέσα στα ζητούμενα όρια
        int batterySize = random.nextInt(4001) + 1000;
        Dimension screenSize = new Dimension(random.nextInt(2501) + 500, random.nextInt(2501) + 500);
        String phoneNumber = String.format("+3069%d",random.nextInt(90000000) + 10000000);
        String manufacturer = manufacturers[random.nextInt(4)];
        int storage = random.nextInt(199) + 2; 
          
        // Αν η τυχαία boolean είναι true, δημιουργούμε specs για feature phone
        if (random.nextBoolean()) {
            phone = new FeaturePhone(batterySize, screenSize, phoneNumber, manufacturer, storage);
        } else { // Αλλιώς δημιουργούμε specs για smart phone
            String operatingSystem = operatingSystems[random.nextInt(3)];
            Camera camera = new Camera(random.nextInt(91) + 10);
            
            phone = new SmartPhone(batterySize, screenSize, phoneNumber, manufacturer, storage, camera, operatingSystem);
        }
        
        // Προσθέτει τα specs στην λίστα phoneList
        phoneList.add(phone);
        
        return phone;
    }
}
