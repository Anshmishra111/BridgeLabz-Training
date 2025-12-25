package method;

public class UnitConverterUtility {
    public static double convertFahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double convertPoundsToKilograms(double pounds) {
        return pounds * 0.453592;
    }

    public static double convertKilogramsToPounds(double kilograms) {
        return kilograms * 2.20462;
    }

    public static double convertGallonsToLiters(double gallons) {
        return gallons * 3.78541;
    }

    public static double convertLitersToGallons(double liters) {
        return liters * 0.264172;
    }

    public static void main(String[] args) {
        System.out.println("Fahrenheit to Celsius (98.6F): " + convertFahrenheitToCelsius(98.6));
        System.out.println("Celsius to Fahrenheit (37C): " + convertCelsiusToFahrenheit(37));
        System.out.println("Pounds to Kilograms (150 lbs): " + convertPoundsToKilograms(150));
        System.out.println("Kilograms to Pounds (68 kg): " + convertKilogramsToPounds(68));
        System.out.println("Gallons to Liters (1 gal): " + convertGallonsToLiters(1));
        System.out.println("Liters to Gallons (1 liter): " + convertLitersToGallons(1));
    }
}



