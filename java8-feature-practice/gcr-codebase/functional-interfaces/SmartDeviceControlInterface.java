package functional_interfaces;
interface SmartDevice{
	void turnOn();
	void turnOff();
}
//light implementation
class Light implements SmartDevice{
	public void turnOn() {
		System.out.println("light turn on");
	}
	public void turnOff()
{
System.out.println("light turn off");		
}
}
class AC implements SmartDevice{
	public void turnOn() {
		System.out.println("AC turned ON");
	}
	 public void turnOff() {
	        System.out.println("AC turned OFF");
	    }
}
class TV implements SmartDevice {
    public void turnOn() {
        System.out.println("TV turned ON");
    }

    public void turnOff() {
        System.out.println("TV turned OFF");
    }
}
public class SmartDeviceControlInterface {
	public static void main(String[] args) {
		SmartDevice light =new Light();
		SmartDevice ac=new AC();
		SmartDevice tv=new TV();
		light.turnOn();
        ac.turnOn();
        tv.turnOn();

        tv.turnOff();
        ac.turnOff();
        light.turnOff();
	}

}
