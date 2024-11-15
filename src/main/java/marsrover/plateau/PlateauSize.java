package marsrover.plateau;

public class PlateauSize {
    private final int x;
    private final int y;

    public PlateauSize(int x, int y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("Plateau size must be greater than (0, 0)");
        }

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String formatSize() {
        return String.format("%s %s", (x + 1), (y + 1));
    }
}
