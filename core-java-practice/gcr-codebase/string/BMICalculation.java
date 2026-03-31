// package core-java-practice.gcr-codebase.string;

public class BMICalculation {
    static String[] calculateBMIAndStatus(double weight, double heightCm) {

        double heightM = heightCm / 100.0;
        double bmi = weight / (heightM * heightM);
        bmi = Math.round(bmi * 100.0) / 100.0;

        String status;
        if (bmi <= 18.4)
            status = "Underweight";
        else if (bmi <= 24.9)
            status = "Normal";
        else if (bmi <= 39.9)
            status = "Overweight";
        else
            status = "Obese";

        return new String[]{String.valueOf(bmi), status};
    }
    static String[][] generateBMIReport(double[][] data) {

        String[][] report = new String[data.length][4];

        for (int i = 0; i < data.length; i++) {
            double weight = data[i][0];
            double height = data[i][1];

            String[] bmiResult = calculateBMIAndStatus(weight, height);

            report[i][0] = String.valueOf(height);      
            report[i][1] = String.valueOf(weight);      
            report[i][2] = bmiResult[0];               
            report[i][3] = bmiResult[1];                
        }
        return report;
    }
    static void displayReport(String[][] report) {

        System.out.println("\nPerson\tHeight(cm)\tWeight(kg)\tBMI\tStatus");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < report.length; i++) {
            System.out.println(
                    (i + 1) + "\t" +
                    report[i][0] + "\t\t" +
                    report[i][1] + "\t\t" +
                    report[i][2] + "\t" +
                    report[i][3]
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int persons = 10;
        double[][] data = new double[persons][2];
        for (int i = 0; i < persons; i++) {
            System.out.println("\nEnter details for Person " + (i + 1));
            System.out.print("Weight (kg): ");
            data[i][0] = sc.nextDouble();
            System.out.print("Height (cm): ");
            data[i][1] = sc.nextDouble();
        }
        String[][] report = generateBMIReport(data);
        displayReport(report);

        sc.close();
    }
}

    

