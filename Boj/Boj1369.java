import java.util.*;
import java.io.*;

public class Boj1369 {
    static int n;
    static int result;
    static int[][] arr;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    static void dp() {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!(arr[i][j] % 2 == 0 || arr[i][j] % 5 == 0)) {
                    arr[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (arr[i][j] == 0)
                    continue;
                for (int k = 0; k < 2; k++) {
                    int nx = i - dx[k];
                    int ny = j - dy[k];
                    if (nx >= 0 && ny >= 0 && dp[nx][ny] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[nx][ny] + 1);
                    }
                }
            }
        }
        result = dp[n - 1][n - 1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp();
        System.out.println(result);
    }
}
