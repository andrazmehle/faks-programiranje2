package izpit2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Drzave {
    public static void main(String[] args) throws FileNotFoundException {
        String fname1 = "src/izpit2/drzave1";
        String fname2 = "src/izpit2/drzave2";
        File f1 = new File(fname1);
        File f2 = new File(fname2);

        Map<String, String[]> drzave = new HashMap<>();

        Scanner sc1 = new Scanner(f1);
        Scanner sc2 = new Scanner(f2);

        while (sc1.hasNextLine()) {
            String[] vrstica = sc1.nextLine().split(";");
            drzave.put(vrstica[0], new String[] {vrstica[1], vrstica[2]});
        }

        while (sc2.hasNextLine()) {
            String[] vrstica = sc2.nextLine().split(";");
            drzave.put(vrstica[0], new String[] {drzave.get(vrstica[0])[0], drzave.get(vrstica[0])[1],vrstica[1], vrstica[2]});
        }

        for (Map.Entry<String, String[]> drzava : drzave.entrySet()) {
            double result = (0.0 + Integer.parseInt(drzava.getValue()[3])) / Integer.parseInt(drzava.getValue()[1]) * 100;
            System.out.printf("%s;%s;%.1f%n", drzava.getValue()[0], drzava.getValue()[2], result);
        }
    }
}
