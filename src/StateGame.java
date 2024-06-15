import java.io.*;

public class StateGame {

    public static void saveGame(Board board, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(board);
            System.out.println("Partie sauvegardée avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde de la partie : " + e.getMessage());
        }
    }

    public static Board loadGame(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Board board = (Board) in.readObject();
            System.out.println("Partie chargée avec succès !");
            return board;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la partie : " + e.getMessage());
            return null;
        }
    }
}
