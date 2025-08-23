//package priprava.izpit1;

import java.util.TreeSet;

class MnozicaZnakov extends TreeSet<Character> {
    public boolean add(Character c) {
        if (!Character.isDigit(c)) return false;
        if (this.contains(c)) return false;
        super.add(c);
        return true;
    }
}

public class Naloga12 {
    public static void main(String[] args) {
        String postavitev = args[0];

        MnozicaZnakov x = new MnozicaZnakov();
        for (int i = 0; i < 9; i++){
            x.clear();
            for (int j = 0; j < 9; j++){
                char c = postavitev.charAt(i*9 + j);
                if (!x.add(c) && c != '0'){
                    System.out.println("Napaka v vrstici " + (i+1) + " (znak '" + c + "')");
                    return;
                }
            }
        }

        MnozicaZnakov y = new MnozicaZnakov();
        for (int i = 0; i < 9; i++) {
            y.clear();
            for (int j = 0; j < 9; j++) {
                char c = postavitev.charAt(i + j*9);
                if (!y.add(c) && c != '0') {
                    System.out.println("Napaka v stolpcu " + (i+1) + " (znak '" + c + "')");
                    return;
                }
            }
        }

        MnozicaZnakov b = new MnozicaZnakov();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b.clear();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        char c = postavitev.charAt(i*3 + j*27 + k*9 + l);
                        if (!b.add(c) && c != '0') {
                            System.out.println("Napaka v kvadratu (" + (i + 1) + "," + (j + 1) + ") (znak '" + c + "')");
                            return;
                        }
                    }
                }
            }
        }

        System.out.println("Postavitev je pravilna");
    }
}
