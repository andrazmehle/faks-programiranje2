package vaje.vaje1;

public class vaje1 {

    // 1.naloga - pravokotniki

    static void pravokotnikStevilVrstice(int sirina, int visina){
        for (int i = 1; i <= visina; i++) {
            System.out.println(Integer.toString(i % 10).repeat(sirina));
        }
    }

    static void pravokotinStevilStolpci(int sirina, int visina){
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= sirina; j++) {
                System.out.print(j % 10);
            }
            System.out.println();
        }
    }

    static void pravokotnik(int odmik, int sirina, int visina){
        for (int i = 1; i <= visina; i++) {
            System.out.print(" ".repeat(odmik));
            System.out.print("X".repeat(sirina));
            System.out.println();
        }
    }

    // 2.naloga - trikotniki

    static void trikotnikStevilVrstice(int visina){
        for (int i = 1; i <= visina; i++) {
            System.out.println(Integer.toString(i % 10).repeat(i));
        }
    }

    static void trikotnikStevilStolpci(int visina){
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    static void trikotnikStevilVrsticeObrnjen(int visina){
        for (int i = visina; i >= 1; i--) {
            System.out.println(Integer.toString(i % 10).repeat(i));
        }

    }

    static void trikotnikStevilStolpciObrnjen(int visina){
        for (int i = visina; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    static void trikotnikStevil(int visina){
        for (int i = 1; i <= visina; i++) {
            System.out.print(" ".repeat(visina - i));
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    static void trikotnik(int odmik, int visina){
        for (int i = 1; i <= visina; i++) {
            System.out.print(" ".repeat(visina - i + odmik));
            System.out.print("*".repeat(2 * i - 1));
            System.out.println();
        }
    }

    static void trikotnikObrnjen(int odmik, int visina){
        for (int i = visina; i >= 1; i--) {
            System.out.print(" ".repeat(visina - i + odmik));
            System.out.print("*".repeat(2 * i - 1));
            System.out.println();
        }
    }

    // 3.naloga - romb

    static void romb(int odmik, int velikost){
        trikotnik(odmik, velikost);
        trikotnikObrnjen(odmik + 1, velikost - 1);
    }

    static void smreka(int v){
        for (int i = 1; i <= v; i++) {
            trikotnik((v - i) * 2, i * 2);
        }
        for (int j = 0; j < 2 * v; j++){
            System.out.print(" ".repeat(v + 1));
            if (v % 2 == 0){
                System.out.print("X".repeat(v + 1));
            }
            else {
                System.out.print("X".repeat(v));
            }
            System.out.println();
        }
    }



    public static void main(String[] args) {
        //pravokotnikStevilVrstice(15, 12);
        //pravokotinStevilStolpci(15, 3);
        //pravokotnik(5, 7, 3);
        //trikotnikStevilVrstice(4);
        //trikotnikStevilStolpci(3);
        //trikotnikStevilVrsticeObrnjen(3);
        //trikotnikStevilStolpciObrnjen(3);
        //trikotnikStevil(5);
        //trikotnik(1, 5);
        //trikotnikObrnjen(5, 5);
        //romb(2, 5);
        //smreka(5);
    }
}
