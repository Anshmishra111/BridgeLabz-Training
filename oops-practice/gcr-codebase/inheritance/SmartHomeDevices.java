package inheritance;

public class SmartHomeDevices {
	    String deviceId;
	    boolean status;

	    SmartHomeDevices(String id, boolean status) {
	        this.deviceId = id;
	        this.status = status;
	    }

	    void displayStatus() {
	        System.out.println("Device ID: " + deviceId + ", Status: " + (status ? "On" : "Off"));
	    }
	}

	class Thermostat extends SmartHomeDevices {
	    int temperatureSetting;

	    Thermostat(String id, boolean status, int temperatureSetting) {
	        super(id, status);
	        this.temperatureSetting = temperatureSetting;
	    }

	    @Override
	    void displayStatus() {
	        super.displayStatus();
	        System.out.println("Temperature: " + temperatureSetting + "°C");
	    }
	}

