package vaje.vaje8.aplikacija;

import vaje.vaje8.banka.Banka;
import vaje.vaje8.banka.Racun;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Upravljanje {

    // Iz podane datoteke prebere podatke o računih in ustvari račune v podani banki.
    private static void dodajRacune(String vir, Banka banka) throws FileNotFoundException {
        Scanner vhod = new Scanner(new File(vir));
        while (vhod.hasNextLine()) {
            String[] podatki = vhod.nextLine().split(";");
            if (podatki[0].equalsIgnoreCase("tekoci")) { // ustvari tekoči račun
                banka.dodajTekociRacun(podatki[1], Double.parseDouble(podatki[2]));
            } else { // ustvari varčevalni račun
                banka.dodajVarcevalniRacun(podatki[1], Double.parseDouble(podatki[2]));
            }
            banka.polog(podatki[1], Double.parseDouble(podatki[3]));
        }
        vhod.close();
    }

    public static void main(String[] args) throws FileNotFoundException {

        // ustvarimo novo banko
        Banka bankaFRI = new Banka();

        // v banki naredimo račune z določenimi stanji, vse podatke preberemo iz datoteke
        dodajRacune("src/vaje/vaje8/viri/racuni.txt", bankaFRI);

        // izpiši vse račune
        System.out.println("VSI RAČUNI:");
        bankaFRI.izpisiRacune(); // uporabimo metodo za izpis vseh računov
        System.out.println();

        // izpiši samo tekoče račune
        System.out.println("TEKOČI RAČUNI:");
        bankaFRI.izpisiRacune(false); // izpis samo tekočih računov
        System.out.println();

        // Testiranje z izbranim računom
        String stevilkaRacuna = "SI56 1234 4321 1234 126"; // primer tekočega računa

        // položi znesek
        System.out.println("POLOG NA RAČUN (" + stevilkaRacuna + ") 50€:");
        bankaFRI.polog(stevilkaRacuna, 50);
        System.out.println("Novo stanje na računu " + stevilkaRacuna + ":");
        bankaFRI.izpisiRacune(); // izpišemo vse račune po pologu
        System.out.println();

        // dvigni znesek
        System.out.println("DVIG Z RAČUNA (" + stevilkaRacuna + ") 30€:");
        bankaFRI.dvig(stevilkaRacuna, 30);
        System.out.println("Novo stanje na računu " + stevilkaRacuna + ":");
        bankaFRI.izpisiRacune(); // izpišemo vse račune po dvigu
        System.out.println();

        // preveri stanje
        System.out.println("STANJE RAČUNA:");
        double stanje = bankaFRI.vrniRacun(stevilkaRacuna).getStanje(); // vrnemo stanje za izbrani račun
        System.out.printf("Stanje na računu %s je %.2f €\n", stevilkaRacuna, stanje);
        System.out.println();

        // po želji: izpis vseh varčevalnih računov
        System.out.println("VARČEVALNI RAČUNI:");
        bankaFRI.izpisiRacune(true); // izpis samo varčevalnih računov
    }
}
