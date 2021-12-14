package eap.abstractfactory;

import java.awt.*;

public class SmartPhone extends Phone {
    private final int batterySize;
    private final Dimension screenSize;
    private final String phoneNumber;
    private final String manufacturer;
    private final int storage;
    private final Camera camera;
    private final String operatingSystem;

    public SmartPhone(int batterySize, Dimension screenSize, String phoneNumber, String manufacturer, int storage, Camera camera, String operatingSystem) {
        this.batterySize = batterySize;
        this.screenSize = screenSize;
        this.phoneNumber = phoneNumber;
        this.manufacturer = manufacturer;
        this.storage = storage;
        this.camera = camera;
        this.operatingSystem = operatingSystem;
    }

    @Override
    public int getBatterySize() {
        return batterySize;
    }

    @Override
    public Dimension getScreenSize() {
        return screenSize;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public int getStorage() {
        return storage;
    }

    public Camera getCamera() {
        return camera;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

   
    // Επεκτείνει την toSting της κληρονομηθείσας κλάσης
    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Camera: " + getCamera() + "\n"
                + "Operating System: " + getOperatingSystem();
    }
     
    
    
}


/*Η κλάση αυτή επεκτείνει την κλάση Phone.
Διαθέτει επιπλέον τα χαρακτηριστικά camera (της δοθείσας αντίστοιχης κλάσης) και το αλφαριθμητικό operatingSystem
Θα πρέπει εντός της υλοποίησής της να υποσκελίσετε (να κάνετε override) τη μέθοδο toString η οποία έχει κληρονομηθεί από την κλάση Phone.
Ωστόσο, στη νέα υλοποίηση της μεθόδου toString Θα πρέπει ο κώδικάς σας να χρησιμοποιεί την κληρονομηθείσα μέθοδο και να συμπληρώνει το
επιστρεφόμενο αποτέλεσμα με τα νέα πεδία που εισήχθησαν στην κλάση SmartPhone.*/

