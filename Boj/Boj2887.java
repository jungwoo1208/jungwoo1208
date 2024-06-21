import java.io.*;
import java.util.*;

public class Boj2887 {
    static int cal(int x1, int y1, int z1, int x2, int y2, int z2) {
        int x = Math.abs(x1 - x2);
        int y = Math.abs(y1 - y2);
        int z = Math.abs(z1 - z2);
        return Math.min(Math.min(x, y), z);
    }

    static int find(int x, int[] parent) {
        int root = x;
        while (root != parent[root]) {
            root = parent[root];
        }
        while (x != root) {
            int next = parent[x];
            parent[x] = root;
            x = next;
        }
        return root;
    }

    static void unionSets(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);
        if (a != b)
            parent[b] = a;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        ArrayList<Pair> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                map.add(new Pair(cal(arr[i][0], arr[i][1], arr[i][2], arr[j][0], arr[j][1], arr[j][2]), i + 1, j + 1));
            }
        }
        Collections.sort(map);

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parent[i] = i;

        long result = 0;
        for (Pair edge : map) {
            int weight = edge.weight;
            int u = edge.u;
            int v = edge.v;
            if (find(u, parent) != find(v, parent)) {
                unionSets(u, v, parent);
                result += weight;
            }
        }
        System.out.println(result);
    }
    static class Pair implements Comparable<Pair> {
        int weight, u, v;
        public Pair(int weight, int u, int v) {
            this.weight = weight;
            this.u = u;
            this.v = v;
        }
        @Override
        public int compareTo(Pair o) {
            return this.weight - o.weight;
        }
    }
}
