public class Ship {
    private final int size;
    private final boolean isHorizontal;
    private final int[] position;
    private final boolean[] hits;
    private final int type;

    public Ship(int size, boolean isHorizontal, int[] position, int type) {
        this.size = size;
        this.isHorizontal = isHorizontal;
        this.position = position;
        this.hits = new boolean[size];
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public int[] getPosition() {
        return position;
    }

    public boolean[] getHits() {
        return hits;
    }

    public boolean isHit(int row, int col) {
        int[] position = this.getPosition();
        boolean isHorizontal = this.isHorizontal();
        int size = this.getSize();

        for (int i = 0; i < size; i++) {
            if ((isHorizontal && row == position[0] && col == position[1] + i) ||
                    (!isHorizontal && row == position[0] + i && col == position[1])) {
                return hits[i];
            }
        }
        return false; // Si la partie du navire n'a pas été trouvée
    }

    public void hit(int index) {
        hits[index] = true;
    }

    public boolean isSunk() {
        for (boolean hit : hits) {
            if (!hit) {
                return false;
            }
        }return true;
    }

    public int getType() {
        return type;
    }


}