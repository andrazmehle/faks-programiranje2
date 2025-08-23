package priprava.grafi;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

class Povezava {
    private String start;
    private String end;
    private int price;

    public Povezava(String s, String e, int p) {
        this.start = s;
        this.end = e;
        this.price = p;
    }

    public String getStart() {
        return this.start;
    }
    public String getEnd() {
        return this.end;
    }
    public int getPrice() {
        return this.price;
    }

    public String toString() {
        return "Zacetek: " + start + ", konec: " + end + ", cena: " + price + ".";
    }
}

public class PotiGrafov {

    static ArrayList<Povezava> preberiPovezave(String imeDatoteke) {
        ArrayList<Povezava> p = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(imeDatoteke))) {

            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                p.add(new Povezava(line[0], line[1], Integer.parseInt(line[2])));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    static ArrayList<String> poisciPot(ArrayList<Povezava> povezave, String zacetek, int kako) {
        ArrayList<String> obiskane = new ArrayList<>();
        String trenutna = zacetek;

        Povezava p = null;
        while (!obiskane.contains(trenutna)) {
            int cena = -1;

            for (Povezava pp : povezave) {
                if (pp.getStart().equals(trenutna)) {
                    if (cena == -1 || (kako == 0 && pp.getPrice() < cena)) {
                        p = pp;
                        cena = pp.getPrice();
                    } else if (kako == 1 && pp.getPrice() > cena) {
                        p = pp;
                        cena = pp.getPrice();
                    }
                }
            }
            obiskane.add(p.getStart());
            trenutna = p.getEnd();
        }
        obiskane.add(p.getEnd());
        return obiskane;
    }

    public static void main(String[] args) {
        for (String s : poisciPot(preberiPovezave("src/priprava/grafi/graf.txt"), "4", 0)) {
            System.out.print(s);
        }
    }
}
