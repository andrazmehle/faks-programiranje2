package kvizi.kviz2;

public class kviz2 {

    static int[] range(int a, int b, int c) {
        int[] r = new int[(int) Math.ceil((b - a + 0.0) / c)];

        int i = 0;
        r[i] = a;
        while (r[i] < b - c) {
            i++;
            r[i] = a + c * (i);
        }
        return r;
    }

    static void rotiraj(int[] tabela, int k) {
        for (int i = 0; i < k; i++) {
            int temp = tabela[0];
            for (int j = 0; j < tabela.length - 1; j++) {
                tabela[j] = tabela[j + 1];
            }
            tabela[tabela.length - 1] = temp;
        }
    }

    public static int[] duplikati(int[] tabela) {
        int[] temp = new int[tabela.length];
        int stUnikatnih = 0;

        for (int i = 0; i < tabela.length; i++) {
            boolean jeDuplikat = false;

            for (int j = 0; j < stUnikatnih; j++) {
                if (tabela[i] == temp[j]) {
                    jeDuplikat = true;
                    break;
                }
            }

            if (!jeDuplikat) {
                temp[stUnikatnih] = tabela[i];
                stUnikatnih++;
            }
        }

        int[] rezultat = new int[stUnikatnih];
        for (int i = 0; i < stUnikatnih; i++) {
            rezultat[i] = temp[i];
        }

        return rezultat;
    }

    static String prevod(String niz) {
        boolean papajscina = true;
        int inc = 0;

        for (int i = 0; i < niz.length(); i++) {

            boolean jeSamoglasnik = false;
            switch (niz.charAt(i)) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    jeSamoglasnik = true;
                    break;
                default:
                    jeSamoglasnik = false;
            }

            if (jeSamoglasnik) {
                if (i + 1 < niz.length() && niz.charAt(i + 1) == 'p' && i + 2 < niz.length() && niz.charAt(i + 2) == 'a') {
                    i += 2;
                } else {
                    papajscina = false;
                    break;
                }
            }

        }

        if (papajscina) {
            String returnNiz = "";

            for (int i = 0; i < niz.length(); i++) {
                returnNiz += niz.charAt(i);

                switch (niz.charAt(i)) {
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'o':
                    case 'u':
                        if (i + 2 < niz.length() && niz.charAt(i + 1) == 'p' && niz.charAt(i + 2) == 'a') {
                            i += 2;
                        }
                        break;
                }
            }
            return returnNiz;
        }
        else {
            String returnNiz = "";

            for (int i = 0; i < niz.length(); i++) {
                returnNiz += niz.charAt(i);

                switch (niz.charAt(i)) {
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'o':
                    case 'u':
                        returnNiz += "pa";
                        break;
                }
            }

            return returnNiz;
        }
    }




    public static void main(String[] args) {
        System.out.println(prevod("Napaka"));
    }
}
