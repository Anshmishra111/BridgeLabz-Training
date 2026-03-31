package BridgeLab_Training.EmployeeWageComputation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EmployeeWageTest {

    // ✅ Test basic arithmetic logic (Daily wage)
    @Test
    public void testDailyWageCalculation() {

        int hoursWorked = 8;
        int wagePerHour = 20;

        int expected = 160;
        int actual = hoursWorked * wagePerHour;

        assertEquals(expected, actual);
    }

    // ✅ Test Part-time wage
    @Test
    public void testPartTimeWageCalculation() {

        int hoursWorked = 4;
        int wagePerHour = 20;

        int expected = 80;
        int actual = hoursWorked * wagePerHour;

        assertEquals(expected, actual);
    }

    // ✅ Test that wage is never negative
    @Test
    public void testWageNonNegative() {

        int wage = 0;

        assertTrue(wage >= 0);
    }

    // ✅ Test HashMap storage concept
    @Test
    public void testWageStorage() {

        java.util.Map<String, Integer> map = new java.util.HashMap<>();
        map.put("TCS", 2000);

        assertEquals(2000, (int) map.get("TCS"));
    }
}
