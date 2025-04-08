//package DOMACE.DN05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DN05 {
    static int sirinaPovrsine;
    static int visinaPovrsine;
    static int stLadij;
    static int[][] postavitev;


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

        stLadij = -1;
        try {
            stLadij = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("Napaka: Manjka podatek o stevilu ladij.");
            System.exit(0);
        }

        postavitev = new int[0][0];
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
        int[][] povrsina = new int[visinaPovrsine][2 * sirinaPovrsine];
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

                if (povrsina[ny][nx] != 0) {
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
                povrsina[ny][nx] = zaporednaStevilka * 10 + stanje;
            }

            zaporednaStevilka++;
        }

        return povrsina;
    }

    public static void izrisiIgralnoPovrsino(int[][] igralnaPovrsina) {
        int visina = igralnaPovrsina.length;
        int sirina = igralnaPovrsina[0].length;

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

                int vrednost = igralnaPovrsina[y][x];

                if (vrednost == 0) {
                    System.out.print("  ");
                } else if (vrednost == 6){
                    System.out.print("o ");
                } else {
                    int stanje = vrednost % 10;
                    switch (stanje) {
                        case 1: System.out.print("p "); break;
                        case 2: System.out.print("t "); break;
                        case 3: System.out.print("X "); break;
                        case 4: System.out.print("x "); break;
                        case 5: System.out.print("@ "); break;
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

    static int[][] povecajIgralnoPovrsino(int[][] postavitev, String noveDimenzije){
        String[] tmp = noveDimenzije.split("x");
        int novaSirina;
        int novaVisina;
        try {
            novaSirina = Integer.parseInt(tmp[0]);
            novaVisina = Integer.parseInt(tmp[1]);
        } catch (NumberFormatException e) {
            return postavitev;
        }
        boolean sx = false;
        boolean vx = false;
        if (novaSirina < sirinaPovrsine) {
            novaSirina = sirinaPovrsine;
            sx = true;
        }
        if (novaVisina < visinaPovrsine) {
            novaVisina = visinaPovrsine;
            vx = true;
        }

        int razlikaX = novaSirina - sirinaPovrsine;
        int razlikaY = novaVisina - visinaPovrsine;

        for (int[] ladja : postavitev){
            if (!sx) {
                ladja[1] += Math.ceil(razlikaX / 2.0);
            }
            if (!vx) {
                ladja[2] += Math.ceil(razlikaY / 2.0);
            }
        }
        sirinaPovrsine = novaSirina;
        visinaPovrsine = novaVisina;

        return postavitev;
    }

    static int[][] minimizirajIgralnoPovrsino(int[][] postavitev, int[][] igralnaPovrsina){
        int maxLevo = sirinaPovrsine - 1;
        int maxDesno = 0;
        int maxGor = visinaPovrsine - 1;
        int maxDol = 0;

        for (int i = 0; i < visinaPovrsine; i++) {
            for (int j = 0; j < sirinaPovrsine * 2; j++) {
                if (igralnaPovrsina[i][j] != 0) {
                    if (j < sirinaPovrsine) {
                        if (j < maxLevo){
                            maxLevo = j;
                        }
                        if (j > maxDesno){
                            maxDesno = j;
                        }
                    }
                    else{
                        if (j - sirinaPovrsine < maxLevo){
                            maxLevo = j - sirinaPovrsine;
                        }
                        if (j - sirinaPovrsine > maxDesno){
                            maxDesno = j - sirinaPovrsine;
                        }
                    }
                    if (i < maxGor){
                        maxGor = i;
                    }
                    if (i > maxDol){
                        maxDol = i;
                    }
                }
            }
        }

        int odstraniLevo = maxLevo;
        int odstraniDesno = sirinaPovrsine - maxDesno - 1;
        int odstraniGor = maxGor;
        int odstraniDol = visinaPovrsine - maxDol - 1;

        for (int ladja[] : postavitev) {
            ladja[1] -= odstraniLevo;
            ladja[2] -= odstraniGor;
        }
        sirinaPovrsine -= odstraniLevo + odstraniDesno;
        visinaPovrsine -= odstraniGor + odstraniDol;

        return postavitev;

    }

    static int[][] simulirajIgro(int[][] igralnaPovrsina, String imeDatoteke){
        int[] ladjeHealth = new int[stLadij];
        int potoplene0 = 0;
        int potoplene1 = 0;

        int i = 0;
        for (int[] ladja : postavitev){
            ladjeHealth[i] = ladja[3];
            i++;
        }


        int naPotezi = 0;

        Scanner sc = null;
        try {
            File f = new File(imeDatoteke);
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            return igralnaPovrsina;
        }

        while (sc.hasNext()) {
            String[] temp = sc.nextLine().split(",");
            int x;
            int y;
            try {
                x = Integer.parseInt(temp[0]) - 1;
                y = Integer.parseInt(temp[1]) - 1;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                continue;
            }

            if (x < 0 || y < 0 || x > sirinaPovrsine || y > visinaPovrsine) {
                continue;
            }

            int pristejX = (naPotezi == 0) ? sirinaPovrsine : 0;
            int vrednost = igralnaPovrsina[y][x + pristejX];

            if (vrednost == 0) {
                igralnaPovrsina[y][x + pristejX] = 6;
                naPotezi = 1 - naPotezi;
                continue;
            }
            else if (vrednost == 6) {
                naPotezi = 1 - naPotezi;
                continue;
            }
            else {
                int stanje = vrednost % 10;
                int stLadje = vrednost / 10 - 1;
                int potopiLadjo = -1;
                switch (stanje) {
                    case 1: {
                        igralnaPovrsina[y][x + pristejX] = vrednost - stanje + 3;
                        ladjeHealth[stLadje] -= 1;
                        potopiLadjo = (ladjeHealth[stLadje] < 1)? stLadje + 1 : -1;
                        break;
                    }
                    case 2: {
                        igralnaPovrsina[y][x + pristejX] = vrednost - stanje + 4;
                        ladjeHealth[stLadje] -= 1;
                        potopiLadjo = (ladjeHealth[stLadje] < 1)? stLadje + 1 : -1;
                        break;
                    }
                    case 3, 4, 5: {
                        naPotezi = 1 - naPotezi;
                        continue;
                    }
                }
                if (potopiLadjo != -1){
                    for (int yy = 0; yy < igralnaPovrsina.length; yy++) {
                        for (int xx = 0; xx < igralnaPovrsina[yy].length; xx++) {
                            if (igralnaPovrsina[yy][xx] / 10 == potopiLadjo) {
                                igralnaPovrsina[yy][xx] = 5;
                            }
                        }
                    }
                    potoplene0 += (naPotezi == 0) ? 1 : 0;
                    potoplene1 += (naPotezi == 1) ? 1 : 0;

                    if (potoplene0 >= stLadij / 2 || potoplene1 >= stLadij / 2) {
                        return igralnaPovrsina;
                    }
                }
            }

        }
        sc.close();
        return igralnaPovrsina;
    }


    static int[][] zasukajLadje(int[][] postavitev, String smerVetra){

        int novaSmer;
        switch (smerVetra){
            case "S": novaSmer = 0; break;
            case "J": novaSmer = 1; break;
            case "V": novaSmer = 2; break;
            case "Z": novaSmer = 3; break;
            default: novaSmer = 0;
        }
        for (int i = 0; i < postavitev.length; i++) {
            postavitev[i][4] = novaSmer;
        }

        return postavitev;
    }


    public static void main(String[] args) {
        String argument0 = args[0];
        String argument1 = args[1];
        String argument2;
        //String argument0 = "simulacija";
        //String argument1 = "src/DOMACE/DN05/vhod.txt";
        //String argument2 = "11x11";
        switch (argument0) {
            case "postavitev": izpisiPostavitev(preberiZacetnoPostavitev(argument1)); break;
            case "povrsina": izrisiIgralnoPovrsino(izdelajIgralnoPovrsino(preberiZacetnoPostavitev(argument1))); break;
            case "povecanje":
                    argument2 = args[2];
                    izrisiIgralnoPovrsino(izdelajIgralnoPovrsino(povecajIgralnoPovrsino(preberiZacetnoPostavitev(argument1), argument2)));
                    break;
            case "zmanjsanje":
                    izrisiIgralnoPovrsino(izdelajIgralnoPovrsino(minimizirajIgralnoPovrsino(preberiZacetnoPostavitev(argument1), izdelajIgralnoPovrsino(preberiZacetnoPostavitev(argument1)))));
                    break;
            case "simulacija": {
                argument2 = args[2];
                //argument2 = "src/DOMACE/DN05/poteze.txt";
                izrisiIgralnoPovrsino(simulirajIgro(izdelajIgralnoPovrsino(preberiZacetnoPostavitev(argument1)), argument2));
                break;
            }
            case "zasuk": {
                argument2 = args[2];
                izrisiIgralnoPovrsino(izdelajIgralnoPovrsino(zasukajLadje(preberiZacetnoPostavitev(argument1), argument2)));
            }
        }
    }
}