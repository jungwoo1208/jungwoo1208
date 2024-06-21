import java.io.BufferedReader;
import java.io.FileReader;
import java.util.EmptyStackException;
import java.util.StringTokenizer;

class ListStack<E>{
    private Node<E> top;
    private int size;
    public ListStack(){
        top=null;
        size=0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void push(E newItem){
        Node newNode=new Node(newItem,top);
        top=newNode;
        size++;
    }
    public E peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.getData();
    }
    public E pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        E topItem=top.getData();
        top=top.getNext();
        size--;
        return topItem;
    }
}
class Node<E>{
    private E data;
    private Node<E> next;
    Node(E data){
        this.data=data;
    }
    Node(E data,Node next){
        this.data=data;
        this.next=next;
    }
    public E getData(){
        return this.data;
    }
    public Node<E> getNext(){
        return this.next;
    }
    public void setData(E newData){
        this.data=newData;
    }
    public void setNext(Node<E> newNext){
        this.next=newNext;
    }
}
public class MainJava  {
    static int in_priority(ListStack stack){
        String s= (String) stack.peek();
        return switch (s) {
            case "(" -> 0;
            case "+", "-" -> 2;
            case "*", "/" -> 4;
            default -> -1;
        };
    }

    static int out_priority(String s){
        return switch (s) {
            case "+", "-" -> 1;
            case "*", "/" -> 3;
            case "(" -> 5;
            default -> -1;
        };
    }

    public static void main(String[]args)throws Exception{
        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\gjw19\\Downloads\\week06_input.txt\\"));
        String s=br.readLine();
        while (s!=null){
            StringTokenizer st=new StringTokenizer(s);
            int n= st.countTokens();
            ListStack stack=new ListStack();
            for(int i=0;i<n;i++){
                String c= st.nextToken();
                if (c.equals(")")){
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        System.out.print(stack.peek()+" ");
                        stack.pop();
                    }
                    if (!stack.isEmpty())
                        stack.pop();
                } else if (c.equals("+")||c.equals("-")||c.equals("*")||c.equals("/")||c.equals("(")) {
                    while (!stack.isEmpty() && in_priority(stack) >= out_priority(c)) {
                        if(!stack.peek().equals("("))
                            System.out.print(stack.peek()+" ");
                        stack.pop();
                    }
                    stack.push(c);
                } else{
                    System.out.print(c+" ");
                }
            }
            while(!stack.isEmpty()) {
                System.out.print(stack.peek()+" ");
                stack.pop();
            }
            System.out.println();
            s= br.readLine();
        }
    }
}
