import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
class Edge implements Comparable<Edge> {//에지를 표현하는 클래스
    int a, b;
    long c;

    public Edge(int a, int b, long c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int compareTo(Edge other) {//우선순위 큐에서 사용하기 위한 compareTo 메소드
        return Long.compare(this.c, other.c);
    }
}

public class Boj1197 {
    static int v, e;
    static long sum = 0;

    static int[] parent;//유니온 파인드를 위한 부모 배열

    static int find(int x) {//유니온 파인드의 find 메소드
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {//유니온 파인드의 union 메소드
        a = find(a);
        b = find(b);
        if (a != b)
            parent[b] = a;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[v + 1];
        for (int i = 1; i <= v; i++)//부모 배열 초기화
            parent[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < e; i++) {//우선순위 큐에 에지들을 넣어줌
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            pq.offer(new Edge(a, b, c));
        }
        int edgeCount = 0;
        while (edgeCount < v - 1 && !pq.isEmpty()) {//크루스칼 알고리즘
            Edge edge = pq.poll();
            int a = edge.a;
            int b = edge.b;
            if (find(a) != find(b)) {//사이클이 발생하지 않는다면
                union(a, b);
                sum += edge.c;
                edgeCount++;
            }
        }
        System.out.println(sum);
    }
}
