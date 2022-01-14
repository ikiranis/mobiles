package eap.simulate;

import eap.abstractfactory.Camera;
import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.Phone;
import eap.abstractfactory.SmartPhone;
import eap.observer.PhoneCreationListener;
import eap.observer.PhoneOrderHandler;
import java.util.HashMap;
import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client implements PhoneCreationListener {
    private final String name;
    private final Class interestedFor;
    private Phone phone;
    /*Για τους παρόχους κινητής τηλεφωνίας έχουμε τις εξής παραδοχές:
    1. Είναι οι 3 που υπάρχουν στην ακόλουθη λίστα
    2. Η Cosmote έχει κινητά που ξεκινάνε 697, 698 και 699, η Vodafone 694, 695 και 696 και η Wind 691,692 και 693.
    3. Κινητά που ξεκινάνε ως 690 θα αναφέρονται ως "Διαφημιστικά".*/
    private static final String[] carriers = {"Wind", "Vodafone", "Cosmote"};

    public Phone getPhone() {
        return phone;
    }

    private void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Client(String name, Class interestedFor) {
        this.name = name;
        this.interestedFor = interestedFor;
    }

    public String getName() {
        return name;
    }

    public Class getInterestedFor() {
        return interestedFor;
    }

    @Override
    public void update(Phone phone) {
        
        // Αν το τηλέφωνο είναι αυτό που ζητάει ο πελάτης και αυτό συνεχίζει να υπάρχει στην λίστα
        if (phone.getClass().equals(interestedFor) && PhoneOrderHandler.phoneExistsInList(phone)) {
            // Ο πελάτης παίρνει το τηλέφωνο
            setPhone(phone);
            // Αφαιρούμε το τηλέφωνο από την λίστα των διαθέσιμων
            PhoneOrderHandler.removePhone(phone);
            // Αφαιρούμε τον πελάτη από την λίστα των listeners
            PhoneOrderHandler.removeListener(this);
            
            // Εκτυπώσεις για το ότι ο πελάτης πήρε το συγκεκριμένο τηλέφωνο
            System.out.printf("\nHi, I am %s (%s) and I got my new phone!\n", name, interestedFor.getSimpleName());
            System.out.println(phone);
            System.out.printf("Phone number valid: %b\n", checkNumberValidity(phone.getPhoneNumber()));
            System.out.printf("Phone number carrier: %s\n", getCarrierName(phone.getPhoneNumber()));
            
            // Χρήση του τηλεφώνου
            usePhone(phone);
        }
    }
    
    /*Η μέθοδος χρήσης του κινητού τηλεφώνου θα κάνει τα εξής:
    Στην περίπτωση FeaturePhone θα καλεί τον αριθμό +30123456789
    Στην περίπτωση SmartPhone Θα βγάζει μια φωτογραφία με φλας και ανάλυση 12 MP*/
    private void usePhone(Phone phone){
        if(phone instanceof FeaturePhone) {
            phone.callNumber("+30123456789");            
        } else {
            // Παίρνουμε το αντικείμενο της κάμερας και θέτουμε Resolution και
            // χρήση του flash, πριν βγάλουμε φωτογραφία
            Camera camera = ((SmartPhone)phone).getCamera();
            camera.setSelectedResolution(12);
            camera.setUseFlash(true);
            camera.takePicture();
        }
    }
    
    // Σύμφωνα με την περιγραφή πάνω από τον πίνακα carriers, η μέθοδος επιστρέφει είτε το όνομα του carrier, είτε Διαφημιστικά
    public String getCarrierName(String phoneNumber){
        // Αρχικοποίηση αντικειμένου HashMap, της κλάσης Map
        Map<String, String> carriersMap = new HashMap<>();
        
        // Προσθήκη στοιχείου στο HashMap με κλειδί "+30690" και τιμή "Διαφημιστικά"
        carriersMap.put("+30690", "Διαφημιστικά");
        
        // Προσθήκη στοιχείων με κλειδί "+3069?" και αντίστοιχη τιμή το όνομα 
        // του carrier
        for(int i=0; i<=2; i++) {
            for (int j=1; j<=3; j++) {
                carriersMap.put(String.format("+3069%d", i*3 + j), carriers[i]);
            }
        }
        
        // Επιστρέφει την τιμή που έχει κλειδί τους 6 πρώτους χαρακτήρες του τηλεφώνου
        // Δηλαδή τον αντίστοιχο carrier
        return carriersMap.get(phoneNumber.substring(0, 6));
    }
    
    /* Για να είναι έγκυρο ένα κινητό τηλέφωνο πρέπει να ισχύουν ταυτόχρονα τα εξής:
    1. Να ξεκινάει από +3069 (είναι όλα από Ελλάδα και είναι όλα κινητά)
    2. Στη συνέχεια να υπάρχουν ακριβώς 10 αριθμοί
    3. Παραδοχή ότι δεν υπάρχουν κενά (white spaces μεταξύ των αριθμών)*/
    public boolean checkNumberValidity(String phoneNumber){
        // Χρήση των κλάσεων Pattern και Matcher για τον έλεγχο του αριθμού
        
        // Δημιουργούμε ένα pattern instance, χρησιμοποιώντας την μέθοδο compile
        // Ψάχνουμε για το pattern, o αριθμός να αρχίζει με +3069 και να ακολουθούν
        // 8 αριθμητικά ψηφία
        Pattern pattern = Pattern.compile("\\+3069\\d{8}");
        
        // Δημιουργούμε ένα matcher instance, για να ελέγξουμε με την μέθοδο 
        // matcher, το string phoneNumber, αν συμφωνεί με το pattern που έχουμε ορίσει
        Matcher matcher = pattern.matcher(phoneNumber);
        
        // Έλεγχος αν συμφωνεί τo string με το pattern κι επιστρέφει true ή false
        return matcher.matches();
    }
}
