package DOMACE.dn10;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;

class Tocka {
    private double x;
    private double y;

    public Tocka(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public double[] midPoint(Tocka a, Tocka b) {
        double[] m = new double[2];
        m[0] = (a.getX() + b.getX()) / 2;
        m[1] = (a.getY() + b.getY()) / 2;
        return m;
    }
}

public class DN10 {
    public static void main(String[] args) {
        StdDraw.setScale(-100, 100);

        //draw starting triangle
        StdDraw.line(-5, -5, 5, -5);
        StdDraw.line(-5, -5, 0, 7);
        StdDraw.line(0, 7, 5, -5);

        Random r = new Random();
        Tocka t = new Tocka(r.nextInt(2, 3), r.nextInt(2, 3));


    }
}
