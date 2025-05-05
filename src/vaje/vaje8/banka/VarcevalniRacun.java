package vaje.vaje8.banka;

public class VarcevalniRacun extends Racun {
    private double obresti; // obrestna mera kot decimalka, npr. 0.0134 za 1.34%

    /**
     * Konstruktor za varčevalni račun.
     *
     * @param stevilka številka računa
     * @param obresti  obrestna mera (npr. 0.0134 za 1.34%)
     */
    public VarcevalniRacun(String stevilka, double obresti) {
        super(stevilka);
        this.obresti = obresti;
    }

    /**
     * Doda obresti trenutnemu stanju računa.
     * Poveča stanje za stanje * obresti.
     */
    public void dodajObresti() {
        double znesekObresti = getStanje() * obresti;
        // uporabimo polog za dosledno logiko in validacijo
        polog(znesekObresti);
    }

    /**
     * Vrne opis računa.
     *
     * @return opis npr. "(varčevalni, obrestna mera: 1,34%)"
     */
    @Override
    public String opisRacuna() {
        // formatiramo obrestno mero v odstotke z dvema decimalnima mestoma
        return String.format("(varčevalni, obrestna mera: %.2f%%)", obresti * 100);
    }
}