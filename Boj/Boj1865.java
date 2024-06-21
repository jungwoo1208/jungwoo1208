import java.util.*;
import java.io.*;
public class Boj1865 {
    static boolean result;
    static int n,m,w;
    static ArrayList<node>node_list;
    static ArrayList<ArrayList<Integer>>map;
    static class node{
        int s,e,t;
        public node(int s, int e, int t){
            this.s=s;
            this.e=e;
            this.t=t;
        }
    }
    static void sol(){

    }
    public static void main(String[]args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int tc= Integer.parseInt(br.readLine());
        for(int i=0;i<tc;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            node_list=new ArrayList<>();
            map=new ArrayList<>();
            result=false;
            n= Integer.parseInt(st.nextToken());
            m=Integer.parseInt(st.nextToken());
            w=Integer.parseInt(st.nextToken());
            for(int j=0;j<n;j++){
                map.add(new ArrayList<>());
            }
            for(int j=0;j<m;j++){
                st=new StringTokenizer(br.readLine());
                int s= Integer.parseInt(st.nextToken());
                int e= Integer.parseInt(st.nextToken());
                int t= Integer.parseInt(st.nextToken());
                map.get(s).add(e);
                map.get(e).add(s);
                node_list.add(new node(s,e,t));
            }
            for(int j=0;j<w;j++){
                st=new StringTokenizer(br.readLine());
                int s= Integer.parseInt(st.nextToken());
                int e= Integer.parseInt(st.nextToken());
                int t= Integer.parseInt(st.nextToken());
                map.get(s).add(e);
                node_list.add(new node(s,e,-t));
            }
            sol();
            if(result)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
