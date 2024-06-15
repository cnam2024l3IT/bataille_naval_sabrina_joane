import java.util.Random;
import java.util.Scanner;

public class UserGame {
    private final Scanner scanner;
    private final Board playerBoard;
    private final Board opponentBoard;


    public UserGame() {
        this.scanner = new Scanner(System.in);
        this.playerBoard = new Board(10);
        this.opponentBoard = new Board(10);
    }

    public void play() {
        // Placement des navires par chaque joueur
        playerBoard.placeShips(scanner);
        opponentBoard.placeShips(scanner);

        // commence la bataille
        startGame();
    }

    public void startGame() {
        System.out.println("Que la bataille commence");

        while (!playerBoard.isGameOver() && !opponentBoard.isGameOver()) {
            // Tour du joueur
            playerTurn();
            if (opponentBoard.isGameOver()) {
                System.out.println("Bravo ! Vous avez coulé tous les navires ennemis !");
                break;
            }
            // Tour de l'adversaire
            opponentTurn();
            if (playerBoard.isGameOver()) {
                System.out.println("Vous avez perdu ! Tous vos navires ont été coulés.");
                break;
            }
        }
    }

    public void playerTurn() {
        System.out.println("Votre tour :");
        playerBoard.displayBoard("Plateau du Joueur 1: ");
        System.out.println("Veuillez entrer les coordonnées du tir (colonne (espace) ligne) :");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        scanner.nextLine();

        String result = opponentBoard.shoot(row, col);
        System.out.println(result);
        opponentBoard.displayBoard("Plateau du joueur 2: ");
    }

    public void opponentTurn() {
        System.out.println("Tour de l'adversaire :");
        opponentBoard.displayBoard("Plateau du joueur 2:");

        Random random = new Random();

        // Générer des coordonnées aléatoires pour le tir de l'adversaire
        int row = random.nextInt(playerBoard.getSize());
        int col = random.nextInt(playerBoard.getSize());

        String result = playerBoard.shoot(row, col);
        System.out.println("L'adversaire tire en (" + (row + 1) + ", " + (col + 1) + ")");
        System.out.println(result);
        playerBoard.displayBoard("Plateau du joueur 1 :");
    }
}
