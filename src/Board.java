import java.io.Serializable;
// import java.util.List;
// import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    private final Box[][] grid;
    private final int size;

    // Constructeur
    public Board(int size) {
        this.size = size;
        this.grid = new Box[size][size];
        initializeGrid();
    }

// Ajouter numérotation et lettre de la grille
    private void initializeGrid() {
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
                grid[i][j] = new Box();
            }
        }
    }

    // Place navire sur le plateau si valide
    public boolean placeShip(Ship ship, int row, int col) {
        // Vérifie si le placement est valide
        if (isValidPlacement(ship, row, col)) {
            // Place le navire sur le plateau
            if (ship.isHorizontal()) {
                for (int i = 0; i < ship.getSize(); i++) {
                    grid[row][col + i].setOccupied(true);
                }
            } else {
                for (int i = 0; i < ship.getSize(); i++) {
                    grid[row + i][col].setOccupied(true);
                }
            }
            return true;
        }
        return false;
    }

// Méthode pour le placement des navires -- revoir la méthode placeships
    public void placeShips(Scanner scanner) {
        // Liste des types de navires avec leurs noms et tailles
        String[] shipNames = {"Torpilleur","Contre-torpilleur", "Croiseur", "Porte-avion"};
        int[] shipSizes = {2, 3, 4, 5};

        int currentPlayer = 1;
        int totalPlayers = 2;

        // Placement des navires pour chaque joueur
        while (currentPlayer <= totalPlayers) {
            System.out.println("Placement des navires pour le Joueur " + currentPlayer + ":");

            // Boucle pour placer les navires
            for (int i = 0; i < 1; i++) {
                // Afficher les types de navires disponibles
                System.out.println("Types de navires disponibles :");
                for (int j = 0; j < shipNames.length; j++) {
                    System.out.println((j + 1) + ". " + shipNames[j] + " (taille " + shipSizes[j] + ")");
                }


                // Demander au joueur de choisir un type de navire
                System.out.println("Veuillez choisir un type de navire en saisissant le numéro correspondant :");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Ignorer le saut de ligne

                // Vérifier si le choix du joueur est valide
                /* if (choice < 1 || choice > shipNames.length) {
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    continue;
                } */

                // Récupérer la taille du navire en fonction du choix du joueur
                int size = shipSizes[choice - 1]; // Taille du navire correspond à l'indice choisi - 1

                // Demander les coordonnées de départ du navire des joueurs
                System.out.println("Veuillez entrer les coordonnées de départ pour placer un navire (ligne (espace) colonne) :");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                scanner.nextLine();

                // Demander l'orientation du navire au joueur
                System.out.println("Veuillez entrer l'orientation du navire (H pour horizontal, V pour vertical) :");
                String orientation = scanner.nextLine().toUpperCase();
                boolean isHorizontal = orientation.equals("H");

                // Créer une instance du navire avec le type choisi
                Ship ship = new Ship(size, isHorizontal, new int[]{row, col}, choice);

                // Placer le navire sur le plateau du joueur
                if (placeShip(ship, row, col)) {
                    System.out.println("Navire placé avec succès !");
                    displayBoard("Plateau du Joueur " + currentPlayer + " après placement :");
                } else {
                    System.out.println("Placement invalide. Veuillez réessayer.");
                }
            }
            // Passer au joueur suivant
            currentPlayer++;
            System.out.println("Lancé jeu .. ");
        }
    }

    // Méthode corrigé
    private boolean isValidPlacement(Ship ship, int row, int col) {
        // Vérifie les limites de la grille
        if (ship.isHorizontal()) {
            if (col + ship.getSize() > size) {
                return false;
            }
        } else {
            if (row + ship.getSize() > size) {
                return false;
            }
        }

        // Définir les limites de la zone à vérifier autour du navire
        int startRow = Math.max(0, row - 1);
        int endRow = ship.isHorizontal() ? Math.min(size - 1, row + 1) : Math.min(size - 1, row + ship.getSize());
        int startCol = Math.max(0, col - 1);
        int endCol = ship.isHorizontal() ? Math.min(size - 1, col + ship.getSize()) : Math.min(size - 1, col + 1);

        // Vérifie les cases du navire et les cases adjacentes
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (grid[i][j].isOccupied()) {
                    return false;
                }
            }
        }

        return true;
    }

    public String shoot(int row, int col) {
        // Vérifier si la case a déjà été touchée
        if (grid[row][col].isHit()) {
            return "Cette cellule a déjà été touchée.";
        }
        // Marquer la case comme touchée
        grid[row][col].setHit(true);
        // Vérifie s'il y a un navire dans la cellule
        if (grid[row][col].isOccupied()) {
            return "Touché, coulé ";
        } else {
            return "Raté, vise mieux ou je te jette à l'eau";
        }
    }

    public void displayBoard(String message) {
        System.out.println(message);
        System.out.println("--------------------"); // Ligne de séparation
        for (int i = 0; i < size; i++) {

            //Ajouter num&rotation et lettre grille

            for (int j = 0; j < size; j++) {
                if (grid[i][j].isHit() && grid[i][j].isOccupied()) {
                    System.out.print("X "); // Cellule touchée avec un navire
                } else if (grid[i][j].isHit()) {
                    System.out.print("- "); // Cellule touchée sans navire
                } else {
                    System.out.print("~ "); // Cellule non touchée
                }
            }
            System.out.println();
        }
        System.out.println(); // Ligne vide après l'affichage du plateau
    }

    public int getSize() {
        return size;
    }

    public boolean isGameOver() {
        // Vérifier si tous les navires ont été coulés
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].isOccupied() && !grid[i][j].isHit()) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
    public Ship[] getShips() {
        List<Ship> hitShips = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Box box = grid[i][j];
                if (box.isOccupied() && box.isHit()) {
                    Ship ship = box.getShip();
                    if (!hitShips.contains(ship)) {
                        hitShips.add(ship);
                    }
                }
            }
        }

        // Convertir la liste en tableau
        Ship[] shipsArray = new Ship[hitShips.size()];
        shipsArray = hitShips.toArray(shipsArray);

        return shipsArray;
    } */

    /*
    public Box getBox(int row, int col) {
        return grid[row][col];
    }

    */
}