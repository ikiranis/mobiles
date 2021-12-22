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

   
    // Επεκτείνει την toString της κληρονομησθείσας κλάσης
    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Camera: " + getCamera() + "\n"
                + "Operating System: " + getOperatingSystem();
    }
      
    
}