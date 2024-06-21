import java.util.*;
import java.io.*;
class Node{
    private int data;
    private Node next;
    Node(int data){
        this.data=data;
    }
    Node(int data,Node next){
        this.data=data;
        this.next=next;
    }
    public int getData(){
        return this.data;
    }
    public Node getNext(){
        return this.next;
    }
    public void setNext(Node newNext){
        this.next =newNext;
    }
}
class CQueue{
    protected Node rear;
    protected int size;
    public CQueue(){
        rear=null;
        size=0;
    }
    void insert(int newItem){
        Node newNode=new Node(newItem);
        if(size==0){
            newNode.setNext(newNode);
        }else {
            newNode.setNext(rear.getNext());
            rear.setNext(newNode);
        }
        rear= newNode;
        size++;
    }
    void delete(){
        if(size==0){
            System.out.print("empty ");
        }else {
            int Item = rear.getNext().getData();
            rear.setNext(rear.getNext().getNext());
            size--;
            System.out.print(Item + " ");
        }
    }
}
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\gjw19\\Downloads\\week07_input.txt\\"));
        String line=br.readLine();
        while (line!=null){
            boolean flag = false;
            StringTokenizer st=new StringTokenizer(line);
            CQueue queue=new CQueue();
            int n= st.countTokens();
            for(int i=0;i<n;i++){
                String token=st.nextToken();
                if(token.equals("i")) {
                    flag = true;
                } else if (token.equals("d")) {
                    flag = false;
                    queue.delete();
                }else {
                    if(flag){
                        queue.insert(Integer.parseInt(token));
                    }
                }
            }
            System.out.println();
            line= br.readLine();
        }
    }
}