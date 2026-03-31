package scenariobased;

public class TemperatureAnalyzer {
	    // Method to analyze temperatures
	    public static void analyzeTemperatures(float[][] temperatures) {

	        float maxTemp = temperatures[0][0];
	        float minTemp = temperatures[0][0];
	        int hottestDay = 0;
	        int coldestDay = 0;

	        for (int day = 0; day < temperatures.length; day++) {
	            float sum = 0;

	            for (int hour = 0; hour < temperatures[day].length; hour++) {
	                float temp = temperatures[day][hour];
	                sum += temp;

	                if (temp > maxTemp) {
	                    maxTemp = temp;
	                    hottestDay = day;
	                }

	                if (temp < minTemp) {
	                    minTemp = temp;
	                    coldestDay = day;
	                }
	            }

	            float average = sum / temperatures[day].length;
	            System.out.println("Average temperature of Day " + (day + 1) +
	                               " = " + average);
	        }

	        System.out.println("Hottest Day  : Day " + (hottestDay + 1));
	        System.out.println("Coldest Day  : Day " + (coldestDay + 1));
	    }

	    public static void main(String[] args) {

	        float[][] weekTemperatures = new float[7][24];

	        // Sample data generation
	        for (int i = 0; i < 7; i++) {
	            for (int j = 0; j < 24; j++) {
	                weekTemperatures[i][j] =
	                        20 + (float) Math.random() * 15;
	            }
	        }

	        analyzeTemperatures(weekTemperatures);
	    }
	}


