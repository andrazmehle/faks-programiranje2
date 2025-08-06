package priprava;

public class izpit2rok {
    int [][] ustvariKvadrat(int n){
        int [][] kvadrat = new int[n][n];
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                kvadrat[i - 1][j - 1] = j + i * n;
            }
        }
        return kvadrat;
    }

    void izpisiKvadrat(int [][] kvadrat, int m){
        int n = kvadrat.length;

        for (int i = 1; i <= n; i++){
            if (i > m && i < n - 1) continue;
            for (int j = 1; j <= n; j++){
                if (j > m && j < n - 1) continue;
                if (j == n - 1 || i == n - 1){
                    System.out.print("   *");
                    continue;
                }
                if (j == n && i == n){
                    System.out.printf("%4d", n * n);
                }
                else if (j == n){
                    System.out.printf("%4d", j + (i - 1) * n);
                }
                else if (i == n){
                    System.out.printf("%4d", (i - 1) * n + j);
                }
                else{
                    System.out.printf("%4d", j + (i - 1) * n);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        izpit2rok izpit = new izpit2rok();
        izpit.izpisiKvadrat(izpit.ustvariKvadrat(12), 5);
    }
}
