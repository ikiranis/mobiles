package eap.abstractfactory;

import java.awt.*;

public class SmartPhoneFactory implements PhoneAbstractFactory {
    private final int batterySize;
    private final Dimension screenSize;
    private final String phoneNumber;
    private final String manufacturer;
    private final int storage;
    private final Camera camera;
    private final String operatingSystem;

    public SmartPhoneFactory(int batterySize, Dimension screenSize, String phoneNumber, String manufacturer, int storage, Camera camera, String operatingSystem) {
        this.batterySize = batterySize;
        this.screenSize = screenSize;
        this.phoneNumber = phoneNumber;
        this.manufacturer = manufacturer;
        this.storage = storage;
        this.camera = camera;
        this.operatingSystem = operatingSystem;
    }

    @Override
    public Phone createPhone() {
        return new SmartPhone(batterySize, screenSize, phoneNumber, manufacturer, storage, camera, operatingSystem);
    }
}

