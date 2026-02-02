package BridgeLabz_Training.Junit;

public class TemperatureConverter {
//celsius to fahrenheit
	public double celsiusToFahrenheit(double celsius) {
		return (celsius*9/5)+32;
	}
	//fahrenheit to celsius
	public double fahrenheitToCelsius(double fahrenheit) {
		return (fahrenheit-32)*5/9;
	}
}
