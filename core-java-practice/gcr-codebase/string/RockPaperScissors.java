// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class RockPaperScissors {
    static String getComputerChoice() {
        int choice = (int) (Math.random() * 3); // 0,1,2

        if (choice == 0)
            return "Rock";
        else if (choice == 1)
            return "Paper";
        else
            return "Scissors";
    }
    static String findWinner(String user, String computer) {

        if (user.equalsIgnoreCase(computer)) {
            return "Draw";
        }

        if (user.equalsIgnoreCase("Rock")) {
            return computer.equals("Scissors") ? "User" : "Computer";
        } else if (user.equalsIgnoreCase("Paper")) {
            return computer.equals("Rock") ? "User" : "Computer";
        } else if (user.equalsIgnoreCase("Scissors")) {
            return computer.equals("Paper") ? "User" : "Computer";
        }

        return "Invalid";
    }
    static String[][] calculateStats(int userWins, int computerWins, int games) {

        String[][] stats = new String[2][3];

        double userPercent = (userWins * 100.0) / games;
        double computerPercent = (computerWins * 100.0) / games;

        stats[0][0] = "User";
        stats[0][1] = String.valueOf(userWins);
        stats[0][2] = String.format("%.2f", userPercent);

        stats[1][0] = "Computer";
        stats[1][1] = String.valueOf(computerWins);
        stats[1][2] = String.format("%.2f", computerPercent);

        return stats;
    }
    static void displayResults(String[][] gameResults, String[][] stats) {

        System.out.println("\nGame Results:");
        System.out.println("Game\tUser Choice\tComputer Choice\tWinner");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < gameResults.length; i++) {
            System.out.println((i + 1) + "\t" +
                    gameResults[i][0] + "\t\t" +
                    gameResults[i][1] + "\t\t" +
                    gameResults[i][2]);
        }

        System.out.println("\nFinal Statistics:");
        System.out.println("Player\tWins\tWinning %");
        System.out.println("-----------------------------");

        for (int i = 0; i < stats.length; i++) {
            System.out.println(stats[i][0] + "\t" +
                    stats[i][1] + "\t" +
                    stats[i][2]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of games: ");
        int games = sc.nextInt();
        sc.nextLine(); 

        String[][] gameResults = new String[games][3];

        int userWins = 0;
        int computerWins = 0;

        for (int i = 0; i < games; i++) {
            System.out.print("\nGame " + (i + 1) + " - Enter your choice (Rock/Paper/Scissors): ");
            String userChoice = sc.nextLine();

            String computerChoice = getComputerChoice();
            String winner = findWinner(userChoice, computerChoice);

            if (winner.equals("User"))
                userWins++;
            else if (winner.equals("Computer"))
                computerWins++;

            gameResults[i][0] = userChoice;
            gameResults[i][1] = computerChoice;
            gameResults[i][2] = winner;
        }

        String[][] stats = calculateStats(userWins, computerWins, games);
        displayResults(gameResults, stats);

        sc.close();
    }
}

    

