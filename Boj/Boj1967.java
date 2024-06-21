import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class node{
    ArrayList<int[]>list=new ArrayList<>();
    void add(int b,int weight){
        list.add(new int []{b,weight});
    }
    public ArrayList<int[]> get_list(){
        return list;
    }
}
class tree{
    node []list;
    public tree(int n){
        list=new node[n+1];
    }
    void add(int a,int b,int weight){
        list[a].add(b,weight);
    }
}
public class Boj1967 {
    void ps(tree tree){
        int tc=tree.list[1].get_list().size();
        int[] len =new int[tc];
        Queue<node>queue=new LinkedList<>();
        for(int i=0;i<tc;i++){

        }
    }
    public static void main(String[]args)throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        tree tree=new tree(n);
        for(int i=0;i<n;i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            int a= Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            tree.add(a,b,weight);
        }

    }
}
