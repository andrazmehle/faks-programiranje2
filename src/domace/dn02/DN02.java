package domace.dn02;

public class DN02 {
    public static void main(String[] args){
        args = new String[]{"5", "3", "4", "2"};

        int s = Integer.parseInt(args[0]);
        int v = Integer.parseInt(args[1]);
        int x = Integer.parseInt(args[2]);
        int y = Integer.parseInt(args[3]);

        for (int i = 0; i < (v - 1) * y + 1; i++) {
            if (i % (v - 1) == 0){
                System.out.println("*".repeat(x * (s - 1) + 1));
            }
            else{
                String l = "*" + " ".repeat(s - 2);
                System.out.println(l.repeat(x) + "*");
            }
        }
    }
}
