package vaje.vaje10;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class CistiRacuni {
    public static void main(String[] args) {
        Dolgovi d = new Dolgovi();

        try {
            d.preberiPrijatelje(new File("src/viri/prijatelji.txt"));
            d.preberiDolgove(new File("src/viri/dolgovi.txt"));
        } catch (IOException e) {
            System.out.println("Napaka pri branju datotek: " + e.getMessage());
            return;
        }

        d.izpisi();

        System.out.println("\nPrijatelji brez dolgov:");
        Set<Prijatelj> brezDolga = d.vrniBrezDolga();
        if (brezDolga.isEmpty()) {
            System.out.println("Vsi prijatelji imajo dolgove.");
        } else {
            for (Prijatelj p : brezDolga) {
                System.out.println(" - " + p);
            }
        }
    }
}
