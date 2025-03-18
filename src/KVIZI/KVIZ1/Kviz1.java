package KVIZI.KVIZ1;

public class Kviz1{

    public static void krog(double r, int d){
        if(r < 0) {
            System.out.println("Napaka: negativen polmer");
            return;
        }
        if(d < 0 ){
            System.out.println("Napaka: negativen d");
            return;
        }

        double obsegKroga = 2 * Math.PI * r;
        double ploscinaKroga = Math.PI * Math.pow(r, 2);

        String format = "Obseg kroga s polmerom r=%.2f je %." + d + "f \n";
        System.out.printf(format, r, obsegKroga);
        System.out.printf(format, r, obsegKroga);

        String format2 = "Ploscina kroga s polmerom r=%.2f je %." + d + "f";
        System.out.printf(format2, r, ploscinaKroga);
    }

    public static String pretvoriSekunde(int sekunde){
        if (sekunde < 0) {
            return "Število sekund ne more biti negativno";
        }

        int ure = sekunde / 3600;
        int minute = sekunde / 60 - ure * 60;

        return String.format("%02d:%02d:%02d", ure, minute, sekunde % 60);
    }

    public static void javaJavaJava(int n) {
        if (n < 0) {
            System.out.println("Napaka: negativen n");
            return;
        }

        String prva = "     J    a   v     v  a   ";
        String druga = "     J   a a   v   v  a a  ";
        String tretja = "  J  J  aaaaa   V V  aaaaa ";
        String cetrta = "   JJ  a     a   V  a     a";

        StringBuilder prva1 = new StringBuilder();
        StringBuilder druga1 = new StringBuilder();
        StringBuilder tretja1 = new StringBuilder();
        StringBuilder cetrta1 = new StringBuilder();

        while (n > 0) {
            prva1.append(prva);
            druga1.append(druga);
            tretja1.append(tretja);
            cetrta1.append(cetrta);
            n--;
        }

        System.out.println(prva1);
        System.out.println(druga1);
        System.out.println(tretja1);
        System.out.println(cetrta1);

    }

    public static boolean jeFibonaccijevo(int n){
        int j = 1;
        int i = 1;
        int k;

        while (i <= n){
            if (n == i){
                return true;
            }
            k = j;
            j = i;
            i = j + k;

        }
        return false;
    }

    public static boolean jePrastevilo(int n){
        if(n < 0){
            return false;
        }

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void izrisiZastavo(int n){
        for (int i = 0; i < 5 * n; i++) {
            if(i < 3 * n){
                if(i % 2 == 0){
                    System.out.print("* ".repeat(n * 2));
                    System.out.print("=".repeat(11 * n + 1) + "\n");
                }
                else{
                    System.out.print(" *".repeat(2 * n - 1) + "  ");
                    System.out.print("=".repeat(11 * n + 1) + "\n");
                }
            }
            else{
                System.out.println("=".repeat(15 * n + 1));
            }

        }
    }

    public static void vDesetisko(int n){
        int vrednost = 0;
        int i = 0;
        int nk = n;
        while (nk > 0) {
            int tmp = nk % 10;
            if (tmp == 8 || tmp == 9) {
                System.out.print("Število " + n + " ni število v osmiškem sistemu (števka " + tmp + ")");
                return;
            }
            vrednost = (int) (vrednost + tmp * Math.pow(8, i));
            i++;
            nk = nk / 10;
        }
        System.out.print(n + "(8) = " + vrednost + "(10)");
    }

    public static String pretvoriVDesetisko(String n, int b){
        for (int i = 0; i < n.length(); i++) {
            if ((int) n.charAt(i) - 55 >= b){
                int tmp = n.charAt(i);
                return "Napaka pri pretvorbi sistema - števka " + n.charAt(i);
            }
            else if ((int) n.charAt(i) < 58 && n.charAt(i) - 48 >= b){
                int tmp = n.charAt(i);
                return "Napaka pri pretvorbi sistema - števka " + n.charAt(i);
            }
        }
            int decimal = Integer.parseInt(n, b);
            return n + "(" + b + ")=" + decimal + "(10)";
    }

    public static int vsotaPrvih(int n){
        int vsota = 0;
        int i = 1;
        while (n >= 0) {
            if (jePrastevilo(i)){
                vsota += i;
                n--;
            }
            i++;
        }
        return vsota - 1;
    }

    public static void main(String[]args){
            //krog(3, 5);
            // System.out.println(pretvoriSekunde(49330));
            //javaJavaJava(5);
            //System.out.println(jeFibonaccijevo(2021));
            //System.out.println(jePrastevilo(15));
            //izrisiZastavo(3);
            //vDesetisko(505);
            //System.out.println(pretvoriVDesetisko("10101010", 2));
            //System.out.println(vsotaPrvih(10));
        }
    }