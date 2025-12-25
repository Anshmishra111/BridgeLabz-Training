package method;
import java.util.Random;
public class EmployeeBonusCalc {
    public static int[][] generateEmployeeData(int count) {
        int[][] data = new int[count][2]; // [][0]=salary, [][1]=years
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            data[i][0] = rand.nextInt(90000) + 10000; // 5-digit salary
            data[i][1] = rand.nextInt(11);            // years of service (0�10)
        }
        return data;
    }

    public static double[][] calculateBonusAndNewSalary(int[][] employeeData) {
        double[][] updated = new double[employeeData.length][2]; // [][0]=new salary, [][1]=bonus

        for (int i = 0; i < employeeData.length; i++) {
            int salary = employeeData[i][0];
            int years = employeeData[i][1];
            double bonusRate = years > 5 ? 0.05 : 0.02;
            double bonus = salary * bonusRate;
            updated[i][1] = bonus;
            updated[i][0] = salary + bonus;
        }
        return updated;
    }

    public static void displaySummary(int[][] oldData, double[][] newData) {
        double totalOld = 0, totalNew = 0, totalBonus = 0;

        System.out.printf("%-10s %-15s %-10s %-15s %-10s", "EmpID", "Old Salary", "Years", "New Salary", "Bonus");
        for (int i = 0; i < oldData.length; i++) {
            System.out.printf("%-10d %-15d %-10d %-15.2f %-10.2f", i + 1, oldData[i][0], oldData[i][1], newData[i][0], newData[i][1]);
            totalOld += oldData[i][0];
            totalNew += newData[i][0];
            totalBonus += newData[i][1];
        }

        System.out.println("Summary:");
        System.out.printf("Total Old Salary: %.2f", totalOld);
        System.out.printf("Total New Salary: %.2f", totalNew);
        System.out.printf("Total Bonus Paid: %.2f", totalBonus);
    }

    public static void main(String[] args) {
        int[][] employeeData = generateEmployeeData(10);
        double[][] updatedData = calculateBonusAndNewSalary(employeeData);
        displaySummary(employeeData, updatedData);
    }
}

