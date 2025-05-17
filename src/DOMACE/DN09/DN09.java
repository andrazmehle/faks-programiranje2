//package DOMACE.DN09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DN09 {
    static Postaja[] postaje;
    static Linija[] linije;
    static Avtobus[] avtobusi;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Napaka: Manjkajoči argumenti!");
            return;
        }

        String datoteka = args[0];
        parser(datoteka);

        if (args.length > 1 && args[1].equals("izpisi")) {
            izpisi();
        }
    }

    public static void parser(String imeDatoteke) {
        try {
            Scanner sc = new Scanner(new File(imeDatoteke));

            String[] prvavrstica = sc.nextLine().split(",");
            int stPostaj = Integer.parseInt(prvavrstica[0]);
            int stLinij = Integer.parseInt(prvavrstica[1]);
            int stAvtobusov = Integer.parseInt(prvavrstica[2]);

            postaje = new Postaja[stPostaj];
            linije = new Linija[stLinij];
            avtobusi = new Avtobus[stAvtobusov];

            if (sc.hasNextLine())
                sc.nextLine();

            int[] vrstniRedLinij = new int[100];
            int stNajdenihLinij = 0;

            for (int i = 0; i < stPostaj; i++) {
                if (!sc.hasNextLine())
                    break;

                String v = sc.nextLine();
                String[] podatki = v.split(",");


                int id = Integer.parseInt(podatki[0]);
                String ime = podatki[1];
                int x = Integer.parseInt(podatki[2]);
                int y = Integer.parseInt(podatki[3]);

                String[] idLinij = podatki[4].split(";");

                for (String idLinijeStr : idLinij) {
                    if (!idLinijeStr.isEmpty()) {
                        int idLinije = Integer.parseInt(idLinijeStr);
                        boolean najdena = false;
                        for (int j = 0; j < stNajdenihLinij; j++) {
                            if (vrstniRedLinij[j] == idLinije) {
                                najdena = true;
                                break;
                            }
                        }
                        if (!najdena) {
                            vrstniRedLinij[stNajdenihLinij] = idLinije;
                            stNajdenihLinij++;
                        }
                    }
                }

                int cakajo = 0;
                if (podatki.length > 6 && !podatki[6].isEmpty()) {
                    cakajo = Integer.parseInt(podatki[6]);
                }

                Postaja p = new Postaja(id, ime, x, y, cakajo);
                postaje[i] = p;

                if (podatki.length > 5 && !podatki[5].isEmpty()) {
                    String[] avtobusiInfo = podatki[5].split(";");
                    for (String busInfo : avtobusiInfo) {
                        if (!busInfo.isEmpty()) {
                            String[] busPodatki = busInfo.split("\\(");
                            if (busPodatki.length >= 2) {
                                int idAvtobusa = Integer.parseInt(busPodatki[0]);
                                int stPotnikov = Integer.parseInt(busPodatki[1].replace(")", ""));

                                Avtobus a = naredBus(idAvtobusa, stPotnikov);
                                a.setTrenutnaPostaja(p);
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < stNajdenihLinij; i++) {
                Linija linija = naredLinijo(vrstniRedLinij[i]);
                linije[i] = linija;
            }

            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            while (sc.hasNextLine()) {
                String v = sc.nextLine();
                if (v.trim().isEmpty()) {
                    continue;
                }

                String[] podatki = v.split(",");
                if (podatki.length < 4) {
                    continue;
                }

                int linijaId = Integer.parseInt(podatki[0]);
                String barva = podatki[1];

                if (!podatki[2].isEmpty()) {
                    String[] busiId = podatki[2].split(";");
                    for (String idAvtobusaStr : busiId) {
                        if (!idAvtobusaStr.isEmpty()) {
                            int busId = Integer.parseInt(idAvtobusaStr);
                            Avtobus a = najdBus(busId);
                            if (a != null) {
                                Linija l = naredLinijo(linijaId);
                                l.dodajAvtobus(a);
                            }
                        }
                    }
                }



                if (!podatki[3].isEmpty()) {
                    String[] idPostajeStr = podatki[3].split("\\|");
                    Linija linija = naredLinijo(linijaId);
                    linija.setBarva(barva);

                    linija.resetPostaj();


                    for (String idStr : idPostajeStr) {
                        if (!idStr.isEmpty()) {
                            int idPostaje = Integer.parseInt(idStr);
                            Postaja p = najdPostajo(idPostaje);
                            if (p != null) {
                                linija.dodajPostajo(p);
                            }
                        }
                    }
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Napaka pri branju datoteke");
        } catch (NumberFormatException e) {
            System.out.println("Napaka pri pretvorbi v število");
        }
    }

    private static Linija naredLinijo(int id) {
        for (int i = 0; i < linije.length; i++) {
            if (linije[i] != null && linije[i].getID() == id) {
                return linije[i];
            }
        }

        Linija nova = new Linija(id);

        for (int i = 0; i < linije.length; i++) {
            if (linije[i] == null) {
                linije[i] = nova;
                break;
            }
        }

        return nova;
    }

    private static Avtobus naredBus(int id, int stPotnikov) {
        for (int i = 0; i < avtobusi.length; i++) {
            if (avtobusi[i] != null && avtobusi[i].getID() == id) {
                return avtobusi[i];
            }
        }

        Avtobus nov = new Avtobus(id, stPotnikov);

        for (int i = 0; i < avtobusi.length; i++) {
            if (avtobusi[i] == null) {
                avtobusi[i] = nov;
                break;
            }
        }

        return nov;
    }

    private static Avtobus najdBus(int id) {
        for (int i = 0; i < avtobusi.length; i++) {
            if (avtobusi[i] != null && avtobusi[i].getID() == id) {
                return avtobusi[i];
            }
        }
        return null;
    }

    private static Postaja najdPostajo(int id) {
        for (int i = 0; i < postaje.length; i++) {
            if (postaje[i] != null && postaje[i].getID() == id) {
                return postaje[i];
            }
        }
        return null;
    }

    public static void izpisi() {
        for (int i = 0; i < linije.length; i++) {
            if (linije[i] != null) {
                System.out.println(linije[i].toString());
            }
        }
    }
}

class Postaja {
    private int ID;
    private String ime;
    private int x;
    private int y;
    private int cakajoci;

    public Postaja(int ID, String ime, int x, int y, int cakajoci) {
        this.ID = ID;
        this.ime = ime;
        this.x = x;
        this.y = y;
        this.cakajoci = cakajoci;
    }

    public int getID() {
        return ID;
    }

    public String getIme() {
        return ime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCakajoci() {
        return cakajoci;
    }

    @Override
    public String toString() {
        return ID + " " + ime + " [" + x + "," + y + "] cakajoci: " + cakajoci;
    }
}

class Avtobus {
    private int ID;
    private int steviloPotnikov;
    private Postaja trenutnaPostaja;

    public Avtobus(int ID, int steviloPotnikov) {
        this.ID = ID;
        this.steviloPotnikov = steviloPotnikov;
        this.trenutnaPostaja = null;
    }

    public int getID() {
        return ID;
    }

    public int getSteviloPotnikov() {
        return steviloPotnikov;
    }

    public Postaja getTrenutnaPostaja() {
        return trenutnaPostaja;
    }

    public void setTrenutnaPostaja(Postaja postaja) {
        this.trenutnaPostaja = postaja;
    }

    @Override
    public String toString() {
        String postaja;
        if (trenutnaPostaja != null) {
            postaja = trenutnaPostaja.getIme();
        } else {
            postaja = "ni na postaji";
        }

        return ID + " (" + steviloPotnikov + ") - " + postaja;
    }
}

class Linija {
    private int ID;
    private String barva;
    private Postaja[] postaje;
    private Avtobus[] avtobusi;
    private int stPostaj;
    private int stAvtobusov;

    public Linija(int ID) {
        this.ID = ID;
        this.barva = "";
        this.postaje = new Postaja[10];
        this.avtobusi = new Avtobus[5];
        this.stPostaj = 0;
        this.stAvtobusov = 0;
    }

    public boolean dodajPostajo(Postaja p) {
        if (stPostaj >= 10) {
            return false;
        }

        for (int i = 0; i < stPostaj; i++) {
            if (postaje[i] != null && postaje[i].getID() == p.getID()) {
                return false;
            }
        }

        postaje[stPostaj] = p;
        stPostaj++;
        return true;
    }

    public void resetPostaj() {
        for (int i = 0; i < postaje.length; i++) {
            postaje[i] = null;
        }
        stPostaj = 0;
    }

    public boolean dodajAvtobus(Avtobus a) {
        if (stAvtobusov >= 5) {
            return false;
        }

        for (int i = 0; i < stAvtobusov; i++) {
            if (avtobusi[i] != null && avtobusi[i].getID() == a.getID()) {
                return false;
            }
        }

        avtobusi[stAvtobusov] = a;
        stAvtobusov++;
        return true;
    }

    public int getID() {
        return ID;
    }

    public String getBarva() {
        return barva;
    }

    public Postaja[] getPostaje() {
        return postaje;
    }

    public Avtobus[] getAvtobusi() {
        return avtobusi;
    }

    public void setBarva(String barva) {
        this.barva = barva;
    }

    @Override
    public String toString() {
        String s = "Linija " + ID + " - ";

        for (int i = 0; i < stPostaj; i++) {
            if (postaje[i] != null) {
                s += postaje[i].getIme();

                boolean imaAvtobus = false;
                for (int j = 0; j < stAvtobusov; j++) {
                    if (avtobusi[j] != null && avtobusi[j].getTrenutnaPostaja() != null &&
                            avtobusi[j].getTrenutnaPostaja().getID() == postaje[i].getID()) {
                        imaAvtobus = true;
                        break;
                    }
                }

                if (imaAvtobus) {
                    s += " (bus)";
                }

                if (i < stPostaj - 1) {
                    s += " -> ";
                }
            }
        }

        return s;
    }
}