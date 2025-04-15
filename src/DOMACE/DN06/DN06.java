package DOMACE.DN06;

import edu.princeton.cs.algs4.StdDraw;

public class DN06 {
    public static void main(String[] args) {

        //String input = args[0];
        String input = "004000000000030002390700080400009001209801307600200008010008053900040000000000800";

        StdDraw.setCanvasSize(600, 600);
        StdDraw.setScale(0, 9);
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.BLACK);

        for (int i = 0; i <= 9; i++) {
            if (i % 3 == 0) {
                StdDraw.setPenRadius(0.005);
            } else {
                StdDraw.setPenRadius(0.001);
            }
            StdDraw.line(0, i, 9, i);
            StdDraw.line(i, 0, i, 9);
        }

        for (int i = 0; i < 81; i++) {
            char c = input.charAt(i);
            if (c != '0') {
                int vrstica = 8 - (i / 9);
                int stolpec = i % 9;
                StdDraw.text(stolpec + 0.5, vrstica + 0.5, String.valueOf(c));
            }
        }
    }
}
