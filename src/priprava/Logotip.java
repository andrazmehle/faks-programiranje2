package priprava;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Logotip {
    public static void main(String[] args) {
        int prvi = 32837969;
        int drugi = 15259182;

        StringBuffer p = new StringBuffer();
        StringBuffer d = new StringBuffer();
        p.append(Integer.toBinaryString(prvi));
        d.append(Integer.toBinaryString(drugi));

        while (p.length() < 25) {
            p.insert(0, "0");
        }
        while (d.length() < 25) {
            d.insert(0, "0");
        }

        StringBuffer s = new StringBuffer();

        for (int i = 0; i < 25; i++) {
            if (p.charAt(i) == '0' && d.charAt(i) == '1') s.append('3');
            else s.append(Character.getNumericValue(p.charAt(i)) + Character.getNumericValue(d.charAt(i)));
        }

        StdDraw.setScale(0,5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                switch (s.charAt(i*5 + j)) {
                    case '0': StdDraw.setPenColor(Color.WHITE); break;
                    case '1': StdDraw.setPenColor(Color.RED); break;
                    case '2': StdDraw.setPenColor(Color.BLACK); break;
                    case '3': StdDraw.setPenColor(Color.GREEN);
                }
                StdDraw.filledRectangle(j+0.5,4 - i + 0.5,0.5,0.5);
            }
        }
    }
}
