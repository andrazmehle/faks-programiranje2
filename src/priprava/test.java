package priprava;
import javax.management.MBeanAttributeInfo;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class test{

    void java() {
        System.out.println("   J    a   v     v  a                                                 \n" +
                "   J   a a   v   v  a a                                                \n" +
                "J  J  aaaaa   V V  aaaaa                                                \n" +
                " JJ  a     a   V  a     a");
    }

    static void kalkulator(int a, int b){
        if(b == 0){
            System.out.println("Napaka: deljenje z 0");
            return;
        }

        System.out.printf("%d + %d = %d \n", a, b, a + b);
        System.out.printf("%d - %d = %d \n", a, b, a - b);
        System.out.printf("%d x %d = %d \n", a, b, a * b);
        System.out.printf("%d / %d = %d \n", a, b, a / b);
        System.out.printf("%d %% %d = %d \n", a, b, a % b);
    }

    static void nicli(int a, int b, int c){
        double d = Math.pow(b, 2) - 4 * a * c;
        if(d < 0){
            System.out.println("Napaka: nicli enacbe ne obstajata ");
            return;
        }

        double x1 = ( -1 * b + Math.sqrt(d)) / (2 * a);
        double x2 = ( -1 * b - Math.sqrt(d)) / (2 * a);

        System.out.printf("x1=%.2f, x2=%.2f", x1, x2);
    }

    void krog(double r, int d){
        if(r < 0){
            System.out.println("Napaka: negativen polmer");
            return;
        }
        if(d < 0){
            System.out.println("Napaka: negativen d");
            return;
        }

        double o = 2 * Math.PI * r;
        double p = Math.pow(r, 2) * Math.PI;

        String format = "Obseg kroga s polmerom r=%.2f je %." + d + "f\n";
        String format2 = "Ploscina kroga s polmerom r=%.2f je %." + d + "f";

        System.out.printf(format, r, o);
        System.out.printf(format2, r, p);

    }

    static String pretvoriSekunde(int sekunde){
        if(sekunde < 0){
            System.out.println("Število sekund ne more biti negativno");
            return "";
        }

        int u = sekunde / 3600;
        int m = sekunde / 60 - u * 60;
        int s = sekunde - u * 3600 - m * 60;

        String format = "%02d:%02d:%02d";

        return String.format(format, u, m, s);
    }

    static void javaJavaJava(int n){
        if(n < 0){
            System.out.println("Napaka: negativen n");
            return;
        }
        System.out.println("     J    a   v     v  a   ".repeat(n));
        System.out.println("     J   a a   v   v  a a  ".repeat(n));
        System.out.println("  J  J  aaaaa   V V  aaaaa ".repeat(n));
        System.out.println("   JJ  a     a   V  a     a".repeat(n));
    }

    static boolean jeFibonaccijevo(int n){
        int x1 = 1;
        int x2 = 1;
        while(x2 <= n){
            if(n == x2){
                return true;
            }
            x2 = x1 + x2;
            x1 = x2 - x1;
        }
        return false;
    }

    static boolean jePrastevilo(int n){
        if(n < 2) return false;

        for(int i = 2; i < n; i++){
            if(n % i == 0) return false;
        }
        return true;
    }

    static void izrisiZastavo(int n) {
        boolean zamik = false;
        for (int i = 0; i < 5 * n; i++) {
            if(i >= 3 * n){
                System.out.println("=".repeat(15 * n + 1));
                continue;
            }
            for (int j = 0; j < 2; j++) {
                if (j == 1) System.out.print("=".repeat(12 * n - (n-1)));
                else if (!zamik) System.out.print("* ".repeat(2 * n));
                else {
                    System.out.print(" *".repeat(2 * n - 1) + "  ");
                }
            }
            System.out.println();
            zamik = !zamik;
        }
    }

    static void vDesetisko(int n){
        int desetisko = 0;
        int osmisko = n;
        int i = 0;
        while(osmisko > 0){
            int ostanek = osmisko % 10;
            if(ostanek > 7){
                System.out.printf("Število %d ni število v osmiškem sistemu (števka %d)\n",n ,ostanek);
                return;
            }
            desetisko += ostanek * Math.pow(8, i);
            osmisko = osmisko / 10;
            i++;
        }
        System.out.printf("%d(8) = %d(10)", n, desetisko);
    }

    static String pretvoriVDesetisko(String n, int b){
        String original = n;
        int rez = 0;

        for(int i = original.length() - 1; i >= 0; i--){
            int c = Character.getNumericValue(original.charAt(i));
            if(c >= b){
                return String.format("Napaka pri pretvorbi sistema - števka %s", original.charAt(i));
            }
            rez += (int) (Math.pow(b, original.length() - i - 1) * c);
        }

        String format = n + "(%d)=%d(10)";
        return String.format(format, b, rez);
    }


    static int vsotaPrvih(int n){
        int stevec = 1;
        int vsota = 0;
        for(int i = 0; stevec <= n; i++){
            if(jePrastevilo(i)){
                vsota += i;
                stevec++;
            }
        }
        return vsota;
    }

    static void pitagoroviTrojcki(int x){
        double c;
        for(int a = 1; a < x; a++){
            for(int b = a + 1; b < x; b++){
                c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                if(c % 1 != 0 || c > x) continue;

                System.out.printf("%d %d %d\n", a, b, (int) c);
            }
        }
    }

    static int[] stik(int[] tabela1, int[] tabela2){
        int[] rez = new int[tabela1.length + tabela2.length];

        for(int i = 0; i < tabela1.length; i++){
            rez[i] = tabela1[i];
        }

        for(int i = 0; i < tabela2.length; i++){
            rez[i + tabela1.length] = tabela2[i];
        }

        return rez;
    }

    static int[] presek(int[] tabela1, int[] tabela2){
        int[] rez = new int[Math.min(tabela1.length, tabela2.length)];
        int c = 0;
        boolean obstaja = false;
        for(int x : tabela1){
            obstaja = false;
            for(int y : tabela2){
                if (x == y) {
                    obstaja = true;
                    break;
                }
            }
            for(int y : rez){
                if (x == y){
                    obstaja = false;
                    break;
                }
            }

            if(obstaja){
                rez[c] = x;
                c++;
            }
        }
        return Arrays.copyOf(rez, c);
    }

    static int pretvoriVSekunde(String cas){
        int sekunde = 0;
        String[] c = cas.split(":");
        for(int i = 0; i < c.length; i++){
            sekunde += (int) (Integer.parseInt(c[i]) * Math.pow(60, 2 - i));
        }
        return sekunde;
    }

    static String izracunajRazliko(String prviCas, String drugiCas){
        int s1 = pretvoriVSekunde(prviCas);
        int s2 = pretvoriVSekunde(drugiCas);
        int razlika = Math.abs(s1 - s2);
        return pretvoriSekunde(razlika);
    }

    static void praDvojcek(int n){
        for (int i = 2; i < n; i++){
            if (jePrastevilo(i) && jePrastevilo(i + 2)){
                System.out.printf("(%d, %d)\n", i, i + 2);
            }
        }
    }

    static void trikotnik(int n, int tip){
        if (tip == 1){
            for (int i = 1; i <= n; i++){
                for (int j = 1; j <= i; j++){
                    System.out.print((j % 10) + " ");
                }
                System.out.println();
            }
        }
        if (tip == 2){
            for (int i = n; i > 0; i--){
                for (int j = 1; j <= n; j++){
                    if (j > n - i) System.out.print(((j - (n - i)) % 10) + " ");
                    else System.out.print("  ");
                }
                System.out.println();
            }
        }
        if (tip == 3){
            for (int i = 1; i <= n; i++){
                for (int j = 1; j <= n; j++){
                    if (j > n - i) System.out.print(((n - j + 1) % 10) + " ");
                    else System.out.print("  ");
                }
                System.out.println();
            }
        }
        if (tip == 4){
            for (int i = n; i > 0; i--){
                for (int j = 1; j <= i; j++){
                    System.out.print(((n + (i - n) - j + 1) % 10) + " ");
                }
                System.out.println();
            }
        }
        if (tip == 5){
            for (int i = 1; i <= n; i++){
                for (int j = 1; j < n * 2; j++){
                    if (Math.abs(j - n) < i) System.out.print(((i - Math.abs(j - n)) % 10) + " ");
                    else System.out.print("  ");
                }
                System.out.println();
            }
        }
        if (tip == 6){
            for (int i = n; i > 0; i--){
                for (int j = 1; j < n * 2; j++){
                    if (Math.abs(j - n) < i) System.out.print(((i - Math.abs(j - n)) % 10) + " ");
                    else System.out.print("  ");
                }
                System.out.println();
            }
        }
        if (tip == 7){
            for (int i = 1; i <= n; i++){
                for (int j = 1; j < n * 2; j++){
                    if (Math.abs(j - n) < i) System.out.print((((i - 1) * 2 + 1 - Math.abs(n - j)) % 10) + " ");
                    else System.out.print("  ");
                }
                System.out.println();
            }
        }
    }

    static void metulj(int n, int tip){
        if (tip == 1){
            for (int i = 1; i <= n; i++){
                for (int j = 1; j < n * 2; j++){
                    if (Math.abs(j - n) >= n - i) System.out.print(((Math.abs(n - Math.abs(n - j))) % 10) + " ");
                    else System.out.print("  ");
                }
                System.out.println();
            }
        }
        if (tip == 2){
            for (int i = 1; i <= n; i++){
                for (int j = 1; j < n * 2; j++){
                    if (Math.abs(j - n) >= i - 1) System.out.print(((Math.abs(n - Math.abs(n - j))) % 10) + " ");
                    else System.out.print("  ");
                }
                System.out.println();
            }
        }
        if (tip == 3){
            for (int i = 1; i <= n; i++){
                for (int j = 1; j < n * 2; j++){
                    if (Math.abs(j - n) >= n - i) System.out.print(((Math.abs(n - Math.abs(n - j))) % 10) + " ");
                    else System.out.print("  ");
                }
                System.out.println();
            }
            for (int i = 2; i <= n; i++){
                for (int j = 1; j < n * 2; j++){
                    if (Math.abs(j - n) >= i - 1) System.out.print(((Math.abs(n - Math.abs(n - j))) % 10) + " ");
                    else System.out.print("  ");
                }
                System.out.println();
            }
        }
    }

    static void veckratnikDelitelj(int a, int b){
        a = Math.abs(a);
        b = Math.abs(b);
        if (a == 0 || b == 0){
            System.out.println("Napaka: obe števili morata biti različni od nič.");
            return;
        }

        int delitelj = 1;
        for (int i = 1; i <= Math.min(a, b); i++){
            if (a % i == 0 && b % i == 0) delitelj = i;
        }

        int veckratnik = Math.max(a, b);
        for (int i = veckratnik; i < a * b; i++){
            if (i % a == 0 && i % b == 0){
                veckratnik = i;
                break;
            }
        }

        System.out.println("Največji skupni delitelj je " + delitelj + ".");
        System.out.println("Najmanjši skupni večkratnik je " + veckratnik + ".");
    }

    static int vsotaStevk(String str){
        int vsota = 0;
        for (int i = 0; i < str.length(); i++){
            int c = str.charAt(i);
            if (Character.isDigit(c)) vsota += c - '0';
        }
        return vsota;
    }

    static boolean preveriRep(String a, String b){
        for (int i = 0; i < a.length(); i++){
            if (a.substring(i).equalsIgnoreCase(b)) return true;
        }
        for (int i = 0; i < b.length(); i++){
            if (b.substring(i).equalsIgnoreCase(a)) return true;
        }
        return false;
    }

    static int[] range(int a, int b, int c){
        if (c <= 0) return new int[0];
        int len = (int) Math.ceil((b - a) / (c + 0.0));
        int[] rez = new int[len];
        int i = 0;
        while (a + i * c < b){
            rez[i] = a + i * c;
            i++;
        }
        return rez;
    }

    static void rotiraj(int [] tabela, int k){
        int [] tabelacp = tabela.clone();
        for (int i = 0; i < tabela.length; i++){
            tabela[i] = tabelacp[Math.abs(i + k) % tabela.length];
        }
    }

    int [] duplikati(int [] tabela){
        int [] rez = new int[tabela.length];
        int k = 0;
        for (int i = 0; i < tabela.length; i++){
            boolean obstaja = false;
            for (int j = i; j >= 0; j--){
                if (tabela[i] == tabela[j] && i != j){
                    obstaja = true;
                    break;
                }
            }
            if (!obstaja){
                rez[k] = tabela[i];
                k++;
            }
        }
        int index = 0;
        for (int i = rez.length - 1; i >= 0; i--){
            if (rez[i] != 0){
                index = i;
                break;
            }
        }
        return Arrays.copyOf(rez, index + 1);
    }


     public static void main(String [] args){
         int[] tabela = new int[]{1,2,3,4,5,6};
         rotiraj(tabela,1);
         String locilo = "";
         for (int e : tabela) {
             System.out.print(locilo + e );
             locilo = ",";
         }
    }
}