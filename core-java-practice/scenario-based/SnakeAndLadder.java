package scenario_based;

public class SnakeAndLadder {
	    public static final int NO_PLAY = 0;
	    public static final int LADDER = 1;
	    public static final int SNAKE = 2;

	    public static void main(String[] args) {

	        System.out.println("Welcome to Snake and Ladder Game");

	        int position = 0;
	        int diceCount = 0;

	        // UC4: Play until player reaches 100
	        while (position < 100) {

	            // UC2: Roll dice
	            int dice = (int) (Math.random() * 6) + 1;
	            diceCount++;

	            // UC3: Player option
	            int option = (int) (Math.random() * 3);

	            switch (option) {

	                case NO_PLAY:
	                    // No change in position
	                    break;

	                case LADDER:
	                    position += dice;
	                    if (position > 100) {
	                        position -= dice; // Do not exceed 100
	                    }
	                    break;

	                case SNAKE:
	                    position -= dice;
	                    if (position < 0) {
	                        position = 0;
	                    }
	                    break;
	            }

	            System.out.println(
	                "Dice Rolled: " + dice +
	                " | Position: " + position
	            );
	        }

	        // UC5: Dice count
	        System.out.println("\nPlayer reached position 100");
	        System.out.println("Total Dice Rolls: " + diceCount);
	    }
	}



