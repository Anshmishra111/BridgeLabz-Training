package functional_interfaces;
interface UnitConverter{
	static double kmToMiles(double km) {
		return km*0.621371;
	}
	static double kgToLbs(double kg) {
		return kg*2.20462;
	}
}

public class UnitConversionTool {
	 public static void main(String[] args) {
		 double km=10;
		 double kg=5;
		 System.out.println("Kilometers to Miles: " +
	                UnitConverter.kmToMiles(km));

	        System.out.println("Kilograms to Pounds: " +
	                UnitConverter.kgToLbs(kg));
	 }

}
