import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int[] MOVE_POWER = { 90, 60, 70, 70 };
    private static final String[] MOVE_NAMES = { "Power Punch", "Super Kick", "Soccer Kick", "Neck Slice" };

public static void main(String[] args) {
    // Step 1 & 2: player & opponents pokemon
    String[] playerPokemon = { "Charizard", "Dragon Claw", "Flare Blitz", "Slash", "Inferno" };
    int[] playerStats = { 150, 60, 80, 40, 90 };
    String[] opponentPokemon = { "Mewtwo", "Confusion", "Psycho Cut", "Aura Sphere", "Low Kick" };
    int[] opponentStats = { 160, 20, 80, 70, 60 };

    // Step 3 & 4: print Welcome Message and stats
    System.out.println("Welcome to the Pokemon Battle Simulation! ");
    printWelcomeMessage(playerPokemon, playerStats, opponentPokemon, opponentStats);

    Random random = new Random();
    int turn = random.nextInt(2); // randomly determine who goes first
    while (playerStats[0] > 0 && opponentStats[0] > 0) { // continue battle until one Pokemon's HP falls below 0
        if (turn == 0) {
            // player's turn
            int move = chooseMove();
            int damage = calculateDamage(MOVE_POWER[move]);
            int remainingOpponentHp = opponentStats[0] - damage;
            System.out.println("You used " + MOVE_NAMES[move] + " and dealt " + damage + " damage!");
            System.out.println("Opponent's remaining HP: " + remainingOpponentHp);
            opponentStats[0] = remainingOpponentHp;
            turn = 1; // switch to opponent's turn
        } else {
            // opponent's turn
            int move = random.nextInt(4); // randomly choose a move for the opponent
            int damage = calculateDamage(MOVE_POWER[move]);
            int remainingPlayerHp = playerStats[0] - damage;
            System.out.println("Opponent used " + MOVE_NAMES[move] + " and dealt " + damage + " damage!");
            System.out.println("Your Pokemon's remaining HP: " + remainingPlayerHp);
            playerStats[0] = remainingPlayerHp;
            turn = 0; // switch to player's turn
        }
    }

    // determine the winner and print the result
    if (playerStats[0] <= 0) {
        System.out.println("Your Pokemon fainted. You lose!");
    } else {
        System.out.println("Opponent's Pokemon fainted. You win!");
    }
}


    public static void printWelcomeMessage(String[] playerPokemon, int[] playerStats, String[] opponentPokemon,
            int[] opponentStats) {
        System.out.println();
        System.out.println("Your Pokemon is " + playerPokemon[0] + " with " + playerStats[0] + " hp!");
        System.out.println("Your opponent's Pokemon is " + opponentPokemon[0] + " with " + opponentStats[0] + " hp!");
    }

    public static int chooseMove() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMove available:");
        for (int i = 0; i < MOVE_NAMES.length; i++) {
            System.out.println((i + 1) + ". " + MOVE_NAMES[i] + " (" + MOVE_POWER[i] + " power)");
        }

        System.out.print("Choose a move (1-4): ");
        int move = scanner.nextInt() - 1;
        while (move < 0 || move >= MOVE_NAMES.length) {
            System.out.println("Invalid move choice. Please choose a move (1-4): ");
            move = scanner.nextInt() - 1;
        }

        return move;
    }

    public static int calculateDamage(int movePower) {
        Random random = new Random();
        int minDamage = (int) (movePower * 0.3);
        int maxDamage = (int) (movePower * 0.5);
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }
}