package priprava;

public class Tocka {
    private int x;
    private int y;

    public Tocka(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public double izracunajRazdaljo(Tocka a) {
        return Math.round(Math.sqrt(Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)) * 10.0) / 10.0;
    }

    public double izracunajRazdaljo(Tocka a, Tocka b) {
        return Math.round(Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(a.getY() - b.getY(), 2)) * 10.0) / 10.0;
    }

    public String toString() {
        return String.format("Tocka (%d,%d) D = %f.1", this.x, this.y, this.izracunajRazdaljo(this));
    }

}
