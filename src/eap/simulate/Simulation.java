package eap.simulate;

import eap.observer.PhoneOrderHandler;
import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.SmartPhone;

import java.util.*;

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
        Random random = new Random();
        int numberOfPhones;
        int numberOfClients;
                
        // Εισαγωγή δεδομένων από τον χρήστη
        System.out.print("Please enter number of phones to be ordered: ");
        numberOfPhones = input.nextInt();
        
        do {
            System.out.print("Please enter number of clients waiting for new phones (MAX: 10): ");
            numberOfClients = input.nextInt();
        } while (numberOfClients>10);
        
        System.out.printf("%d phone orders have been placed by the PhoneShop!\n", numberOfPhones);
        System.out.printf("%d clients are waiting to buy a new phone!\n", numberOfClients);
        
        // Δημιουργία των πελατών
        for (int i=0; i<numberOfClients; i++) {
            // Θέτουμε τυχαία αν το interestedFor είναι κλάσης FeaturePhone ή SmartPhone
            Class interestedFor = random.nextBoolean() ? FeaturePhone.class : SmartPhone.class;
            
            // Δημιουργούμε τον πελάτη και τον προσθέτουμε στην λίστα των listeners,
            // για να ενημερωθεί όταν παραχθεί τον κινητό που τον ενδιαφέρει
            PhoneOrderHandler.addListener(new Client(names[random.nextInt(10)], interestedFor));
        }
        
        // Δημιουργία των τηλεφώνων
        PhoneShop phoneShop = new PhoneShop();
                
        for(int i=0; i<numberOfPhones; i++) {
            // Το phoneShop δημιουργεί τα specs και τα περνάει στην κλάση
            // PhoneOrderHandler για τα δημιουργήσει
            PhoneOrderHandler.addPhone(phoneShop.createPhoneSpec());
        }
        
        // Καλούμε την μέθοδο buildPhones για να δημιουργήσει όλα τα phones
        PhoneOrderHandler.buildPhones();
        
        // Report για τους χρήστες που δεν πήραν τηλέφωνο
        PhoneOrderHandler.printClientsWithoutPhone();
        
    }

}
