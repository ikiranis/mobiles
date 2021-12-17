package eap.simulate;


import eap.abstractfactory.Camera;
import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.FeaturePhoneFactory;
import eap.abstractfactory.Phone;
import eap.abstractfactory.PhoneAbstractFactory;
import eap.abstractfactory.SmartPhone;
import eap.abstractfactory.SmartPhoneFactory;

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
        PhoneAbstractFactory factory;
        Phone phone;
        
        int phoneKind = random.nextInt(2);
        int batterySize = random.nextInt(4000 + 1000);
        Dimension screenSize = new Dimension(random.nextInt(2000), random.nextInt(3000));
        String phoneNumber = "+306957344455";
        String manufacturer = manufacturers[random.nextInt(4)];
        int storage = random.nextInt(64);
                
        if (phoneKind == 0) {
            factory = new FeaturePhoneFactory(batterySize, screenSize, phoneNumber, manufacturer, storage);
            phone = factory.createPhone();
        } else {
            String operatingSystem = operatingSystems[random.nextInt(3)];
            Camera camera = new Camera(10);
            
            factory = new SmartPhoneFactory(batterySize, screenSize, phoneNumber, manufacturer, storage, camera, operatingSystem);
            phone = factory.createPhone();
        }
        
        phoneList.add(phone);
        
        return phone;
    }
}
