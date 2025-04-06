package KVIZI.KVIZ2;

public class kviz2 {

    static int vsotaStevk(String str) {
        int vsota = 0;
        for (int i = 0; i < str.length(); i++) {
            try {
                vsota += Integer.parseInt(str.substring(i, i + 1));
            } catch (Exception e) {
                continue;
            }
        }
        return vsota;
    }

    static boolean preveriRep(String a, String b) {
        boolean check = false;

        if (a.length() < b.length()) {
            for (int i = 0; i < a.length() - 1; i++) {
                if (Character.toLowerCase(a.charAt(a.length() - i - 1)) != Character.toLowerCase(b.charAt(b.length() - i - 1))) {
                    check = false;
                    break;
                }
                check = true;
            }
        } else {
            for (int i = 0; i < b.length() - 1; i++) {
                if (Character.toLowerCase(a.charAt(a.length() - i - 1)) != Character.toLowerCase(b.charAt(b.length() - i - 1))) {
                    check = false;
                    break;
                }
                check = true;
            }
        }
        return check;
    }

    static int[] range(int a, int b, int c) {

    }

    public static void main(String[] args) {
        //System.out.println(vsotaStevk("1abc2"));
        System.out.println(preveriRep("DAN","Dan na dan"));
    }
}
