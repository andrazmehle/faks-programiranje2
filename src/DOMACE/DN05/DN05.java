package DOMACE.DN05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DN05 {
    static int sirinaPovrsine;
    static int visinaPovrsine;


    static int[][] preberiZacetnoPostavitev(String imeDatoteke) {
        Scanner sc = null;
        try {
            File f = new File(imeDatoteke);
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Napaka: datoteka ne obstaja.");
            System.exit(0);
        }
        if (!sc.hasNext()) {
            System.out.println("Napaka: Manjka podatek o dimenzijah igralne povrsine.");
            System.exit(0);
        }
            String[] sirinaInVisina = sc.nextLine().split("x");
        if (sirinaInVisina[0].isEmpty()) {
            System.out.println("Napaka: Manjka podatek o dimenzijah igralne povrsine.");
            System.exit(0);
        }
        else if (sirinaInVisina.length != 2) {
            System.out.println("Napaka: Nepravilen podatek o dimenzijah igralne povrsine.");
            System.exit(0);
        }
        try {
            Integer.parseInt(sirinaInVisina[0]);
            Integer.parseInt(sirinaInVisina[1]);
        } catch (NumberFormatException e) {
            System.out.println("Napaka: Nepravilen podatek o dimenzijah igralne povrsine.");
            System.exit(0);
        }
        if (Integer.parseInt(sirinaInVisina[0]) <= 0 || Integer.parseInt(sirinaInVisina[1]) <= 0) {
            System.out.println("Napaka: Dimenzija mora biti pozitivna.");
            System.exit(0);
        }
        else{
            sirinaPovrsine = Integer.parseInt(sirinaInVisina[0]);
            visinaPovrsine = Integer.parseInt(sirinaInVisina[1]);
        }

        int stLadij = -1;
        try {
            stLadij = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("Napaka: Manjka podatek o stevilu ladij.");
            System.exit(0);
        }

        int[][] postavitev = new int[0][0];
        if (stLadij < 0) {
            System.out.println("Napaka: Stevilo ladij ne sme biti negativno.");
            System.exit(0);
        }
        else{
            postavitev = new int[stLadij][5];
        }

        int i = 0;
        while (sc.hasNext()) {
            for (int j = 0; j < 4; j++) {
                try {
                    postavitev[i][j] = sc.nextInt();
                    if (j == 0 && postavitev[i][j] != 0 && postavitev[i][j] != 1) {
                        System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                        System.exit(0);
                        }
                    if (postavitev[i][j] < 0 || postavitev[i][j] > sirinaPovrsine || postavitev[i][j] > visinaPovrsine) {
                        System.out.println("Napaka: Nepravilen podatek o postavitvi ladje.");
                        System.exit(0);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Napaka: Podatek o stevilu ladij se ne ujema s stevilom vnosov.");
                    System.exit(0);
                }
            }

            switch (sc.next()) {
                case "S": postavitev[i][4] = 0; break;
                case "J": postavitev[i][4] = 1; break;
                case "V": postavitev[i][4] = 2; break;
                case "Z": postavitev[i][4] = 3; break;
                default: System.out.println("Napaka: Nepravilen podatek o postavitvi ladje."); System.exit(0); break;
            }
            i++;
        }

        if (stLadij != i){
            System.out.println("Napaka: Podatek o stevilu ladij se ne ujema s stevilom vnosov.");
            System.exit(0);
        }
        sc.close();
        return postavitev;
    }

    static void izpisiPostavitev(int[][] postavitev){
        for (int i = 0; i < visinaPovrsine; i++) {
            System.out.print("Igralec: " + postavitev[i][0]);
            System.out.print("  Dolzina: " + postavitev[i][3]);
            System.out.print("  Smer: " + postavitev[i][4]);
            System.out.print("  Koordinate premca: (" + postavitev[i][1] + "," + postavitev[i][2] + ")");
            System.out.println();
        }
    }

    static int[][] izdelajIgralnoPovrsino(int[][] postavitev) {
        int[][] povrsina = new int[2 * sirinaPovrsine][visinaPovrsine];
        int zaporednaStevilka = 1;

        for (int[] ladja : postavitev) {
            int igralec = ladja[0];
            int x0 = ladja[1];
            int y0 = ladja[2];
            int dolzina = ladja[3];
            int smer = ladja[4];

            int dx = 0, dy = 0;
            if (smer == 0) dy = 1;
            else if (smer == 1) dy = -1;
            else if (smer == 2) dx = -1;
            else if (smer == 3) dx = 1;

            int zamikX = (igralec == 1) ? sirinaPovrsine : 0;
            int premecX = x0 + zamikX;
            int premecY = y0;

            boolean veljavna = true;


            for (int i = 0; i < dolzina; i++) {
                int nx = premecX + dx * i;
                int ny = premecY + dy * i;

                if (nx < 0 || nx >= 2 * sirinaPovrsine || ny < 0 || ny >= visinaPovrsine) {
                    veljavna = false;
                    break;
                }

                if (igralec == 0 && nx >= sirinaPovrsine) {
                    veljavna = false;
                    break;
                }
                if (igralec == 1 && nx < sirinaPovrsine) {
                    veljavna = false;
                    break;
                }

                if (povrsina[nx][ny] != 0) {
                    veljavna = false;
                    break;
                }
            }

            if (!veljavna) {
                zaporednaStevilka++;
                continue;
            }

            for (int i = 0; i < dolzina; i++) {
                int nx = premecX + dx * i;
                int ny = premecY + dy * i;

                int stanje = (i == 0) ? 1 : 2;
                povrsina[nx][ny] = zaporednaStevilka * 10 + stanje;
            }

            zaporednaStevilka++;
        }

        return povrsina;
    }

    public static void izrisiIgralnoPovrsino(int[][] igralnaPovrsina) {
        int visina = igralnaPovrsina[0].length;
        int sirina = igralnaPovrsina.length;

        for (int i = 0; i < sirina + 3; i++) {
            System.out.print("# ");
        }
        System.out.println();

        for (int y = 0; y < visina; y++) {
            System.out.print("# ");

            for (int x = 0; x < sirina; x++) {
                if (x == sirina / 2) {
                    System.out.print("# ");
                }

                int vrednost = igralnaPovrsina[x][y];

                if (vrednost == 0) {
                    System.out.print("  ");
                } else {
                    int stanje = vrednost % 10;
                    if (stanje == 1) {
                        System.out.print("p ");
                    } else if (stanje == 2) {
                        System.out.print("t ");
                    }
                }
            }

            System.out.println("#");
        }

        for (int i = 0; i < sirina + 3; i++) {
            System.out.print("# ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        //String argument0 = args[0];
        //String argument1 = args[1];
        String argument0 = "povrsina";
        String argument1 = "src/DOMACE/DN05/vhod.txt";
        //String argument2 = "11x11";
        if (Objects.equals(argument0, "postavitev")){
            izpisiPostavitev(preberiZacetnoPostavitev(argument1));
        }
        else if (Objects.equals(argument0, "povrsina")){
            izrisiIgralnoPovrsino(izdelajIgralnoPovrsino(preberiZacetnoPostavitev(argument1)));
        }
        else if (Objects.equals(argument0, "povecanje")){
            //String argument2 = args[2];
            //izrisiIgralnoPovrsino(izdelajIgralnoPovrsino(povecajIgralnoPovrsino(preberiZacetnoPostavitev(argument1), argument2)));
        }
    }
}