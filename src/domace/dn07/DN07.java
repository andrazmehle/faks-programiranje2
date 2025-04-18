//package domace.dn07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Planet {
    String imePlaneta;
    int radijPlaneta;

    Planet(String imePlaneta, int radijPlaneta) {
        this.imePlaneta = imePlaneta;
        this.radijPlaneta = radijPlaneta;
    }

    double povrsina(int radijPlaneta){
        return Math.pow(radijPlaneta, 2) * Math.PI * 4;
    }
}

public class DN07 {
    public static void main(String[] args) {
        String imeDatoteke = args[0];
        String argument2 = args[1];
        //String imeDatoteke = "src/domace/dn07/planeti.txt";
        //String argument2 = "mars+uranu+neptun";
        String[] iskaniPlaneti = argument2.split("\\+");

        File f = new File(imeDatoteke);
        Scanner sc = null;
        try{
            sc = new Scanner(f);
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }

        Planet[] planeti = new Planet[8];

        int i = 0;
        while (sc.hasNextLine()){
            String[] planet = sc.nextLine().split(":");
            planeti[i] = new Planet(planet[0], Integer.parseInt(planet[1]));
            i++;
        }


        long vsota = 0;
        for (String iskanPlanet : iskaniPlaneti){
            for (Planet planet : planeti){
                if (planet.imePlaneta.equalsIgnoreCase(iskanPlanet)){
                    vsota += planet.povrsina(planet.radijPlaneta);
                    break;
                }
            }
        }

        long vsotaMiljoni = vsota / 1000000;

        System.out.println("Povrsina planetov \"" + argument2 + "\" je " + vsotaMiljoni + " milijonov km2");
    }
}
