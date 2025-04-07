package VAJE.VAJE5.zbirke;

import VAJE.VAJE5.nakupovanje.NakupovalniSeznam;

public class Seznam {
    static String[] seznam;
    static int stElementov = 0;

    public static boolean narediSeznam(int n){
        if (seznam == null){
            seznam = new String[n];
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean dodajNaKonecSeznama(String element){
        try {
            seznam[stElementov] = element;
            stElementov++;
            return true;
            } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static void izpisiSeznam(){
        if (seznam == null){
            System.out.println("NAPAKA: seznam ne obstaja.");
        }
        System.out.println("Na seznamu so naslednji elementi: ");
        for (int i = 0; i < stElementov; i++) {
            System.out.println((i + 1) + ": " + seznam[i]);
        }
    }

    public static String odstraniIzSeznama(int mesto){
        String element = null;
        if (seznam[mesto] == null){
            return element;
        }
                element = seznam[mesto];
                for (int i = mesto; i < stElementov; i++) {
                    seznam[i] = seznam[i + 1];
                }
                stElementov -= 1;
        return element;
    }

    public static boolean dodajVSeznam(String element, int mesto){
        if (mesto < 1) {
            return false;
        }
        if (mesto > stElementov){
            dodajNaKonecSeznama(element);
            return true;
        }
        try {
            for (int i = mesto - 1; i < stElementov; i++) {
                seznam[i + 1] = seznam[i];
            }
            seznam[mesto - 1] = element;
            stElementov += 1;
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static int dolzinaSeznama(){
        if (seznam == null){
            return -1;
        }
        return stElementov;
    }

    public static boolean uniciSeznam(){
        if (seznam == null){
            return false;
        }
        for (int i = 0; i < stElementov; i++) {
            seznam[i] = null;
        }
        return true;
    }

}
