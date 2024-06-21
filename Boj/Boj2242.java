import java.util.*;
import java.io.*;
public class Boj2242 {
    static int[] list;
    static int s;
    static int n;
    static ArrayList<ArrayList<Integer>> tree;
    void makeTree(){
        tree = new ArrayList<>();
        for(int i=1;i<=n-2;i++){
            for(int j=1;j<n-i-1;j++){
                boolean[] visited = new boolean[n];
                ArrayList<Integer> temp = new ArrayList<>();
            }
        }

    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new int[n];
        s=0;
        for(int i=0;i<n;i++){
            list[i] = Integer.parseInt(br.readLine());
            s+=list[i];
        }
        s/=2;
    }
}
