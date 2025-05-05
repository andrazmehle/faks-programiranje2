package vaje.vaje8.banka;

public class Banka {
    private Racun[] racuni;
    private int steviloRacunov;

    public Banka() {
        this.racuni = new Racun[500];
        this.steviloRacunov = 0;
    }

    public boolean dodajTekociRacun(String stevilka, double limit) {
        if (steviloRacunov >= racuni.length || obstajaStevilka(stevilka)) {
            return false;
        }
        racuni[steviloRacunov++] = new TekociRacun(stevilka, limit);
        return true;
    }

    public boolean dodajVarcevalniRacun(String stevilka, double obresti) {
        if (steviloRacunov >= racuni.length || obstajaStevilka(stevilka)) {
            return false;
        }
        racuni[steviloRacunov++] = new VarcevalniRacun(stevilka, obresti);
        return true;
    }

    private boolean obstajaStevilka(String stevilka) {
        return najdiRacun(stevilka) != null;
    }

    private Racun najdiRacun(String stevilka) {
        for (int i = 0; i < steviloRacunov; i++) {
            if (racuni[i].getStevilka().equals(stevilka)) {
                return racuni[i];
            }
        }
        return null;
    }

    public void izpisiRacune() {
        izpisiRacune(null);
    }

    public void izpisiRacune(Boolean varcevalni) {
        int stevec = 0;
        for (int i = 0; i < steviloRacunov; i++) {
            Racun r = racuni[i];
            if (varcevalni == null ||
                    (varcevalni && r instanceof VarcevalniRacun) ||
                    (!varcevalni && r instanceof TekociRacun)) {
                System.out.println(r);
                stevec++;
            }
        }
        System.out.println("Število izpisanih računov: " + stevec);
    }

    /**
     * Izvede dvig zneska iz računa s podano številko.
     */
    public boolean dvig(String stevilka, double znesek) {
        Racun r = najdiRacun(stevilka);
        if (r == null) return false;
        return r.dvig(znesek);
    }

    /**
     * Izvede polog zneska na račun s podano številko.
     */
    public boolean polog(String stevilka, double znesek) {
        Racun r = najdiRacun(stevilka);
        if (r == null) return false;
        return r.polog(znesek);
    }

    /**
     * Doda obresti vsem varčevalnim računom.
     */
    public void dodajObresti() {
        for (int i = 0; i < steviloRacunov; i++) {
            if (racuni[i] instanceof VarcevalniRacun) {
                ((VarcevalniRacun) racuni[i]).dodajObresti();
            }
        }
    }

    public Racun vrniRacun(String stevilka) {
        return najdiRacun(stevilka);
    }

}
