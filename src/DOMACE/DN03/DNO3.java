package DOMACE.DN03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class DNO3 {

    static String createPass(String fname, int n, int s) throws FileNotFoundException {
        String[] besede = new String[100];
        File f = new File(fname);
        Scanner sc = new Scanner(f);

        int i = 0;
        while (sc.hasNextLine()){
            besede[i] = sc.nextLine();
            i++;
        }
        sc.close();

        Random r = new Random(s);
        String pass = "";
        while (i < n){
            String beseda = besede[r.nextInt(i)];
            pass += beseda.charAt(r.nextInt(beseda.length()));

            i++;
        }
        return pass;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //String fileName = args[0];
        //int n = Integer.parseInt(args[1]);
        //int seed = Integer.parseInt(args[2]);

        String fileName = "gesla.txt";
        int n = 10;
        int seed = 100;

        System.out.println(createPass(fileName, n, seed));

    }
}
