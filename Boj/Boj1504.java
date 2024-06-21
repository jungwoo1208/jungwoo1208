import java.io.*;
import java.util.*;

public class Boj1504{
    static int n,e;
    static int[][]map;
    static class Node implements Comparable<Node> {
        int x;
        int weight;
        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        int[] result = new int[n + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[start] = 0;
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int x = poll.x;
            if (visited[x]) continue;
            visited[x] = true;
            for (int i = 1; i <= n; i++) {
                if (map[x][i] > 0 && result[i] > result[x] + map[x][i]) {
                    result[i] = result[x] + map[x][i];
                    pq.offer(new Node(i, result[i]));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        e= Integer.parseInt(st.nextToken());
        map=new int[n+1][n+1];
        for(int i=0;i<e;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int len=Integer.parseInt(st.nextToken());
            map[x][y]=len;
            map[y][x]=len;
        }
        st=new StringTokenizer(br.readLine());
        int n1= Integer.parseInt(st.nextToken());
        int n2= Integer.parseInt(st.nextToken());
        int[][] distToX = new int [n+1][];
        distToX[1]=dijkstra(1);
        distToX[n1]=dijkstra(n1);
        distToX[n2]=dijkstra(n2);
        distToX[n]=dijkstra(n);
        int result=Math.min(distToX[1][n1]+distToX[n1][n2]+distToX[n2][n],distToX[1][n2]+distToX[n2][n1]+distToX[n1][n]);
        if(result>=Integer.MAX_VALUE)
            result=-1;
        if(result<0)
            result=-1;
        System.out.println(result);
    }
}