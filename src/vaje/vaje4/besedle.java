package vaje.vaje4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class besedle {

    // Konstante barv
    static final int BELA = 0;
    static final int CRNA = 1;
    static final int RUMENA = 2;
    static final int ZELENA = 3;

    // ANSI ukazi (za barvni izpis)
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_GREEN_BG = "\u001b[42m";
    static final String ANSI_YELLOW_BG = "\u001b[43m";
    static final String ANSI_WHITE_BG = "\u001b[47;1m";
    static final String ANSI_BLACK_BG = "\u001b[40m";
    static final String ANSI_WHITE = "\u001b[37m";
    static final String ANSI_BLACK = "\u001b[30m";

    static final String abeceda = "ABCČDEFGHIJKLMNOPRSŠTUVZŽ"; // Veljavne črke
    static final int MAX_POSKUSOV = 6; // Število poskusov

    static String[] seznamBesed; // Seznam vseh možnih besed
    static String iskanaBeseda; // Iskana beseda trenutne igre
    static int[] barveAbecede; // Barve črk pri izpisu abecede

    static Scanner sc = new Scanner(System.in);

    // Izpiše znak v izbrani barvi
    static void izpisiZBarvo(char znak, int barva) {
        String slog;
        if (barva == ZELENA) {
            slog = ANSI_BLACK + ANSI_GREEN_BG;
        } else if (barva == RUMENA) {
            slog = ANSI_BLACK + ANSI_YELLOW_BG;
        } else if (barva == BELA) {
            slog = ANSI_BLACK + ANSI_WHITE_BG;
        } else {
            slog = ANSI_WHITE + ANSI_BLACK_BG;
        }
        System.out.print(slog + " " + znak + " " + ANSI_RESET);
    }

    // Prebere seznam besed iz datoteke
    static void preberiBesede(String datoteka) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(datoteka));
        seznamBesed = new String[Integer.parseInt(sc.nextLine()) + 1];
        int i = 0;
        while (sc.hasNextLine()) {
            seznamBesed[i] = sc.nextLine().toUpperCase();
            i++;
        }
        sc.close();
    }

    // Pripravi vse za novo igro
    static void novaIgra() {
       Random r = new Random();
       barveAbecede = new int[abeceda.length()];
       iskanaBeseda = seznamBesed[r.nextInt(seznamBesed.length)];
    }

    // Izpiše abecedo
    static void izpisiAbecedo() {
        for (int i = 0; i < abeceda.length(); i++) {
            izpisiZBarvo(abeceda.charAt(i), barveAbecede[i]);
        }
    }

    // Ali je beseda veljavna?
    static boolean veljavnaBeseda(String beseda) {
        if (beseda.length() != 5){
            System.out.println("Nepravilna dolzina besede");
            return false;
        }
        boolean flag;
        for (int i = 0; i < beseda.length(); i++) {
            flag = false;
            for (int j = 0; j < abeceda.length(); j++) {
                if (beseda.charAt(i) == abeceda.charAt(j)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("V besedi so neveljavni znaki");
                return false;
            }
            else {
                return true;
            }

        }
        return false;
    }

    // Določi barve črk v ugibani besedi
    static int[] pobarvajBesedo(String ugibanaBeseda) {
        for (int i = 0; i < ugibanaBeseda.length(); i++) {
            boolean r = false;

            if (ugibanaBeseda.charAt(i) == iskanaBeseda.charAt(i)) {
                for (int j = 0; j < barveAbecede.length; j++) {
                    if (ugibanaBeseda.charAt(i) == barveAbecede[j]) {
                        barveAbecede[j] = ZELENA;
                    }
                }
            }
            for (int k = 0; k < barveAbecede.length; k++) {
                if (ugibanaBeseda.charAt(i) == barveAbecede[k]) {
                    if (barveAbecede[k] != ZELENA) {
                        barveAbecede[k] = RUMENA;
                    }
                    r = true;
                }
            }
            if (!r) {
                for (int j = 0; j < barveAbecede.length; j++) {
                    if (ugibanaBeseda.charAt(i) == barveAbecede[j]) {
                        barveAbecede[j] = CRNA;
                    }
                }
            }
        }
            return new int[0];
    }

    // Izpiši besedo
    static void izpisiBesedo(String ugibanaBeseda, int[] barve) {
        // TODO: implementiraj
    }


    // Izvede eno igro
    static void igra() {
        novaIgra();
        System.out.println(iskanaBeseda);

        int poskus = 1;
        boolean uganil = false;
        while (poskus <= MAX_POSKUSOV) {
            izpisiAbecedo();
            System.out.printf("Poskus %d/%d: ", poskus, MAX_POSKUSOV);
            String ugibanaBeseda = sc.nextLine().toUpperCase();

            // Preveri veljavnost
            if (!veljavnaBeseda(ugibanaBeseda))
                continue;

            // Pobarvaj crke v besedi (namigi)
            int[] barve = pobarvajBesedo(ugibanaBeseda);

            // Izpiši pobarvano besedo
            izpisiBesedo(ugibanaBeseda, barve);

            if (ugibanaBeseda.equals(iskanaBeseda)) {
                uganil = true;
                break;
            }
            poskus++;
        }

        if (uganil) {
            System.out.printf("Bravo! Besedo si uganil/a v %d poskusih.\n", poskus);
        } else {
            System.out.printf("Žal ti ni uspelo. Pravilna beseda: %s\n", iskanaBeseda);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        preberiBesede("src/VAJE/VAJE4/viri/besede.txt");

        while (true) {
            igra();
            System.out.print("Nova igra? (d/n): ");
            String odg = sc.nextLine();
            if (odg.toLowerCase().charAt(0) != 'd') {
                break;
            }
        }
    }
}
