import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            // Création des plateaux de jeu pour chaque joueur
            Board playerBoard = new Board(10); // Plateau du joueur
            Board opponentBoard = new Board(10); // Plateau de l'adversaire

            // Affichage initial des plateaux de jeu
            playerBoard.displayBoard("Plateau du joueur :");

            // Faire condition controle quand plateau joueur, afficher le plateau  adversaire
            //opponentBoard.displayBoard("\nPlateau de l'adversaire :");

            // Faire un controle sur quand le joueur aura placé c'est navire
            // Si les navires sont placé, affiche le plateau du joueur et les navire  placé

            // Placement des navires par chaque joueur
            playerBoard.placeShips(scanner);
            opponentBoard.placeShips(scanner);

            UserGame userGame = new UserGame();
            userGame.play();

            // Demande au joueur s'il veut rejouer
            System.out.println("Voulez-vous rejouer ? (O/N)");
            String userChoice = scanner.nextLine().toUpperCase();
            playAgain = userChoice.equals("O");
        }

        System.out.println("Merci d'avoir joué au jeu de la bataille navale !");
        scanner.close();
    }
}
