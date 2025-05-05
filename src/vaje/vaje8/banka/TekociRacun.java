package vaje.vaje8.banka;

public class TekociRacun extends Racun {
    private double limit; // največji dovoljeni znesek za en dvig

    /**
     * Konstruktor za tekoči račun.
     *
     * @param stevilka številka računa
     * @param limit največji dovoljeni znesek za en dvig
     */
    public TekociRacun(String stevilka, double limit) {
        super(stevilka);
        this.limit = limit;
    }

    /**
     * Izvede dvig, če je znesek manjši ali enak limitu.
     *
     * @param znesek - znesek, ki ga želimo dvigniti
     * @return true, če je dvig uspešen in znotraj limita, sicer false
     */
    @Override
    public boolean dvig(double znesek) {
        if (znesek > limit) {
            return false;
        }
        return super.dvig(znesek);
    }

    /**
     * Vrne opis računa.
     *
     * @return opis v obliki "(tekoči, limit: 250,00 EUR)"
     */
    @Override
    public String opisRacuna() {
        return String.format("(tekoči, limit: %.2f EUR)", limit);
    }
}