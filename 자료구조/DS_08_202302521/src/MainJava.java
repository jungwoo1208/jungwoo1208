import java.io.*;
class Node{
    int parent;
    int size;
    public Node(int Parent , int Size){
        this.parent=Parent;
        this.size=Size;
    }
    public int getParent() {
        return parent;
    }

    public int getSize() {
        return size;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
class UnionFind{
    protected Node[]a;
    public UnionFind(Node[]iarray){
        a=iarray;
    }
    protected int find (int i){
        if(i!=a[i].getParent())
            a[i].setParent(find(a[i].getParent()));
        return a[i].getParent();
    }
    public void union(int i,int j){
        int iroot =find(i);
        int jroot=find(j);
        if(a[iroot].getSize()>=a[jroot].getSize()){
            a[iroot].setParent(j);
            a[j].setSize(a[j].getSize()+a[iroot].getSize());
        }else {
            a[jroot].setParent(i);
            a[i].setSize(a[jroot].getSize()+a[i].getSize());
        }
    }
    public void printsets() {
        int len=a.length;
        int cnt=0;
        int k=0;
        while (cnt!=len){
            boolean flag =false;
            for(int i=0;i<len;i++){
                if(find(a[i].getParent())==k){
                    System.out.print(i+" ");
                    flag=true;
                    cnt++;
                }
            }
            if(flag)
                System.out.println();
            k++;
        }
    }
}
public class MainJava {
    public static void main(String[]args)throws Exception{
        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\gjw19\\Downloads\\week08_input.txt\\"));
        String line= br.readLine();
        int digit = Integer.parseInt(line);
        Node []a=new Node[digit];
        for(int i=0;i<digit;i++)
            a[i]=new Node(i,1);
        UnionFind uf=new UnionFind(a);
        line= br.readLine();
        while (line!=null){
            String []str=line.split(" ");
            int p=Integer.parseInt(str[0]);
            int q=Integer.parseInt(str[1]);
            uf.union(p,q);
            line= br.readLine();
        }
        uf.printsets();
        br.close();
    }
}
