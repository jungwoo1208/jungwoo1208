import java.util.*;
public class Boj11444 {
    static int n;
    static ArrayList<Integer>arr=new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        long[] fb_arr = new long[n+1];
        fb_arr[0] = 0;
        fb_arr[1] = 1;
        for (int i = 2; i <=n; i++) {
            fb_arr[i] = fb_arr[i - 1]% 1000000007 + fb_arr[i - 2]%1000000007;
        }
        System.out.println(fb_arr[n]);
    }
}

