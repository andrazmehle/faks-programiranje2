package vaje.vaje10;

import java.util.*;

public class Prijatelj implements Comparable<Prijatelj> {
    private static int stevec = 1;
    private final int stevilka;
    private final String ime;
    private final Map<Prijatelj, Double> dolguje;

    public Prijatelj(String ime) {
        this.ime = ime;
        this.stevilka = stevec++;
        this.dolguje = new HashMap<>();
    }

    public String getIme() {
        return ime;
    }

    public Map<Prijatelj, Double> getDolguje() {
        return dolguje;
    }

    public void dodajDolg(Prijatelj p, double znesek) {
        dolguje.put(p, dolguje.getOrDefault(p, 0.0) + znesek);
    }

    public String toString() {
        return String.format("[%03d] %s", stevilka, ime);
    }

    public String vrniOpisZDolgovi() {
        if (dolguje.isEmpty()) {
            return this + " nima dolgov.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this).append(" ima dolgove do naslednjih prijateljev:\n");
        for (Map.Entry<Prijatelj, Double> vnos : dolguje.entrySet()) {
            sb.append(String.format("  --> %s (%.2f EUR)\n", vnos.getKey(), vnos.getValue()));
        }
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prijatelj)) return false;
        Prijatelj p = (Prijatelj) o;
        return ime.equals(p.ime);  // za potrebe iskanja po imenu
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime);
    }

    @Override
    public int compareTo(Prijatelj other) {
        return this.ime.compareTo(other.ime);
    }
}
