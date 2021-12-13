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

    }
}
