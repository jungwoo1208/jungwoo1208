import java.io.*;
import java.util.*;
public class Boj1753 {
    public static int v,e,k;
    public static int[]arr;
    public static boolean []check;
    public static ArrayList<edge>list[];
    public static PriorityQueue<edge>pq=new PriorityQueue<edge>();
    static class edge implements Comparable<edge>{
        int end;
        int weight;
        edge(int end,int weight){
            this.end=end;
            this.weight=weight;
        }
        public int compareTo(edge o){
            if(this.weight>o.weight) return 1;
            else return -1;
        }
    }
    public static void main(String[] args) throws  Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        v= Integer.parseInt(st.nextToken());
        e= Integer.parseInt(st.nextToken());
        k= Integer.parseInt(br.readLine());
        arr=new int[v+1];
        check=new boolean[v+1];
        list=new ArrayList[v+1];
        for(int i=1;i<=v;i++){
            list[i]=new ArrayList<edge>();
        }
        for(int i=0;i<=v;i++){
            arr[i]=Integer.MAX_VALUE;
        }
        for(int i=0;i<e;i++){
            st=new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[a].add(new edge(b,w));
        }
        pq.add(new edge(k,0));
        arr[k]=0;
        while(!pq.isEmpty()){
            edge tmp=pq.poll();
            if(check[tmp.end]) continue;
            check[tmp.end]=true;
            for(edge next:list[tmp.end]){
                if(arr[next.end]>arr[tmp.end]+next.weight){
                    arr[next.end]=arr[tmp.end]+next.weight;
                    pq.add(new edge(next.end,arr[next.end]));
                }
            }
        }
        for(int i=1;i<=v;i++){
            if(arr[i]==Integer.MAX_VALUE) {
                System.out.println("INF");
                continue;
            }
            System.out.println(arr[i]);
        }
    }
}
