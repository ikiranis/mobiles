package eap.simulate;

import eap.observer.PhoneOrderHandler;
import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.Phone;
import eap.abstractfactory.SmartPhone;

import java.util.*;
import java.util.List;

public class Simulation {
    //Fixed array of Greek names
    static final String[] names = {"George","Maria","Dimitris","John","Katerina",
                            "Panagiotis","Konstantinos","Christina","Eleni","Petros"};

    
    // Στη μέθοδο main το πρόγραμμα αρχικά ζητά από τον χρήστη να δηλώσει τον αριθμό
    // των κινητών τηλεφώνων που πρόκειται να δοθούν στη συνέχεια στη γραμμή παραγωγής,
    // καθώς και τον αριθμό των πελατών που θα δημιουργηθούν
    // και θα αναμείνουν για την ενδεχόμενη παραγωγή των τηλεφώνων που τους ενδιαφέρουν
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfPhones;
        int numberOfClients;
        Phone phone;
                
        // Εισαγωγή δεδομένων από τον χρήστη
        System.out.print("Please enter number of phones to be ordered: ");
        numberOfPhones = input.nextInt();
        
        do {
            System.out.print("Please enter number of clients waiting for new phones (MAX: 10): ");
            numberOfClients = input.nextInt();
        } while (numberOfClients>10);
        
        System.out.printf("%d phone orders have been placed by the PhoneShop!\n", numberOfPhones);
        System.out.printf("%d clients are waiting to buy a new phone!\n", numberOfClients);
        
        // Δημιουργία των τηλεφώνων από το PhoneShop
        PhoneShop shop = new PhoneShop();
                
        for(int i=0; i<numberOfPhones; i++) {
            PhoneOrderHandler.addPhone(shop.createPhoneSpec());
        }
        
        PhoneOrderHandler.buildPhones();
        
    }

}
