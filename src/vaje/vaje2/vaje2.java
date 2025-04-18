package vaje.vaje2;

public class vaje2 {

    static long fakultetaL(int n){
        long fakulteta = 1;
        for (int i = 1; i <= n; i++) {
            fakulteta *= i;
        }
        return fakulteta;
    }

    static long stirlingL(int n){
        double fakulteta = Math.sqrt(2 * Math.PI * n) * Math.pow((n / Math.E), n);
        return Math.round(fakulteta);
    }

    static double fakultetaD(int n){
        double fakulteta = 1;
        for (int i = 1; i <= n; i++) {
            fakulteta *= i;
        }
        return fakulteta;
    }

    static double stirlingD(int n){
        return Math.sqrt(2 * Math.PI * n) * Math.pow((n / Math.E), n);
    }

    static void izpis(){
        double napaka;
        System.out.println("  n              n!            Stirling(n)      napaka (%)");
        System.out.println("----------------------------------------------------------");

        for (int i = 1; i <= 20; i++) {
            long fL = fakultetaL(i);
            long sL = stirlingL(i);
            napaka = (fL - sL + 0.0) / fL;

            System.out.printf("%3d", i);
            System.out.printf("%21d", fL);
            System.out.printf("%21d", sL);
            System.out.printf("%12.7f", napaka * 100);
            System.out.println();
        }
    }

    static void izpisD(){
        double napaka;

        System.out.println("  n         n!            Stirling(n)     napaka (%)");
        System.out.println("----------------------------------------------------");

        for (int i = 1; i <= 100; i++) {
            double fL = fakultetaD(i);
            double sL = stirlingD(i);
            napaka = (fL - sL + 0.0) / fL;

            System.out.printf("%3d", i);
            System.out.printf("%18.9E", fL);
            System.out.printf("%18.9E", sL);
            System.out.printf("%12.7f", napaka * 100);
            System.out.println();
        }
    }


    public static void main(String[] args) {
        //System.out.println(fakultetaL(10));
        //System.out.println(stirlingL(10));

        //izpis();
        izpisD();
    }
}
