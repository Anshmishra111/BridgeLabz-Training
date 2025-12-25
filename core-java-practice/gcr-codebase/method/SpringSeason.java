package method;

public class SpringSeason {
	static boolean isSpringSeason(int month, int day) {
        return (month == 3 && day >= 20) || (month == 6 && day <= 20) || (month > 3 && month < 6);
    }

    public static void main(String[] args) {
        int month = Integer.parseInt(args[0]);
        int day = Integer.parseInt(args[1]);
        boolean result = isSpringSeason(month, day);
        if (result) {
            System.out.println("It's a Spring Season");
        } else {
            System.out.println("Not a Spring Season");
        }
    }
}
