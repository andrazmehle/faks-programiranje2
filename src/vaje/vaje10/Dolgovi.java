package vaje.vaje10;

import java.io.*;
import java.util.*;

public class Dolgovi {
    private final List<Prijatelj> prijatelji = new ArrayList<>();

    public void preberiPrijatelje(File datoteka) throws IOException {
        try (Scanner sc = new Scanner(datoteka)) {
            while (sc.hasNextLine()) {
                String vrstica = sc.nextLine().trim();
                if (!vrstica.isEmpty()) {
                    prijatelji.add(new Prijatelj(vrstica));
                }
            }
        }
    }

    public void preberiDolgove(File datoteka) throws IOException {
        try (Scanner sc = new Scanner(datoteka)) {
            while (sc.hasNextLine()) {
                String vrstica = sc.nextLine().trim();
                if (vrstica.isEmpty()) continue;

                String[] deli = vrstica.split(";");
                if (deli.length != 3) continue;

                String imeDolznik = deli[0].trim();
                String imeUpnik = deli[1].trim();
                double znesek;

                try {
                    znesek = Double.parseDouble(deli[2].trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                if (znesek < 0) {
                    // zamenjaj dolžnika in upnika
                    String temp = imeDolznik;
                    imeDolznik = imeUpnik;
                    imeUpnik = temp;
                    znesek = -znesek;
                }

                Prijatelj dolznik = najdiPrijatelja(imeDolznik);
                Prijatelj upnik = najdiPrijatelja(imeUpnik);

                if (dolznik != null && upnik != null) {
                    dolznik.dodajDolg(upnik, znesek);
                }
            }
        }
    }

    private Prijatelj najdiPrijatelja(String ime) {
        for (Prijatelj p : prijatelji) {
            if (p.getIme().equals(ime)) return p;
        }
        return null;
    }

    public void izpisi() {
        for (Prijatelj p : prijatelji) {
            System.out.println(p.vrniOpisZDolgovi());
        }
    }

    public Set<Prijatelj> vrniBrezDolga() {
        Set<Prijatelj> brezDolgov = new TreeSet<>();
        for (Prijatelj p : prijatelji) {
            if (p.getDolguje().isEmpty()) {
                brezDolgov.add(p);
            }
        }
        return brezDolgov;
    }
}
