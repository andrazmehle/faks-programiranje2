package KVIZI.KVIZ2;

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

    static void rotiraj(int [] tabela, int k) {
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



    public static void main(String[] args) {

    }
}
