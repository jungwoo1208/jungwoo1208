import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9663 {
    static int N;
    static int[] Map;
    static int cnt = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Map = new int[N];
        nQueen(0);
        System.out.println(cnt);
    }

    static void nQueen(int depth) {
        if(depth == N) {
            cnt++;
            return;
        }
        for(int i = 0; i < N; i++) {
            Map[depth] = i;
            if(isPossible(depth)) {
                nQueen(depth+1);
            }
        }
    }

    static boolean isPossible(int col) {
        for(int i = 0; i < col; i++) {
            if(Map[col] == Map[i]) {
                return false;
            } else if(Math.abs(col - i) == Math.abs(Map[col] - Map[i])) {
                return false;
            }
        }
        return true;
    }
}
