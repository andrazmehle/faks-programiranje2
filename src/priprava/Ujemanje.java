package priprava;

public class Ujemanje {
    String poisciUjemanje(String prvi, String drugi) {
        String match = "";
        for (int i = 1; i < prvi.length(); i++) {
            for (int j = i; j < prvi.length(); j++) {
                if (drugi.contains(prvi.substring(i, j)) && match.length() < prvi.substring(i, j).length()) {
                    match = prvi.substring(i, j);
                }
            }

        }
        return match;
    }

    public static void main(String[] args) {
        Ujemanje u = new Ujemanje();
        System.out.println(u.poisciUjemanje("Danes je lep dan", "Dandanes je lepo"));
    }
}
