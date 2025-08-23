package priprava.izpit1;//package priprava;

public class Naloga11 {

    int pretvoriZnak(String znak){
        int vsota = 0;
        for (int i = 0; i < znak.length(); i++){
            vsota += Character.getNumericValue(znak.charAt(i)) * Math.pow(2, (znak.length() - i - 1));
        }
        return vsota;
    }

    String pretvoriZnake(String znaki){
        StringBuffer sb = new StringBuffer();
        for (int i  = 0; i < znaki.length(); i += 8){
            sb.append((char) pretvoriZnak(znaki.substring(i, i+8)));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Naloga11 p = new Naloga11();
        System.out.println(p.pretvoriZnake(args[0]));
    }
}
