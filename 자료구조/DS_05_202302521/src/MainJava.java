import java.util.*;
import java.io.*;
class ArrayStack<E>{
    private E s[];
    private int top;
    public ArrayStack(int n){
        s=(E[])new Object[n];
        top=-1;
    }
    public int size(){
        return top+1;
    }
    public boolean isEmpty(){
        return  top==-1;
    }
    public E peak(){
        if(isEmpty())throw new EmptyStackException();
        return s[top];
    }
    public void push(E newItem){
        if(size()==s.length)
            return;
        s[++top]=newItem;
    }
    public E pop(){
        if(isEmpty())
            throw new EmptyStackException();
        E item=s[top];
        s[top--]=null;
        return item;
    }
}
public class MainJava {
    public static void main(String[]args)throws Exception{
        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\gjw19\\Downloads\\input.txt"));
        String s= br.readLine();
        while(s != null){
            int size=50;
            ArrayStack<Integer> A= new ArrayStack<>(size);
            ArrayStack<Integer> B= new ArrayStack<>(size);
            StringTokenizer st=new StringTokenizer(s);
            int n=st.countTokens();
            B.push(Integer.parseInt(st.nextToken()));
            for(int i=0;i<n-1;i++){
                int tmp=B.pop();
                int in=Integer.parseInt(st.nextToken());
                if(tmp<=in){
                    B.push(tmp);
                    B.push(in);
                }else {
                    B.push(tmp);
                    while(true){
                        if(B.isEmpty()){
                            B.push(in);
                            break;
                        }
                        tmp=B.pop();
                        if(tmp<in){
                            B.push(tmp);
                            B.push(in);
                            break;
                        }
                        A.push(tmp);
                    }
                    while (!A.isEmpty()){
                        tmp=A.pop();
                        B.push(tmp);
                    }
                }
            }
            while (!B.isEmpty()){
                System.out.print(B.pop()+" ");
            }
            System.out.println();
            s= br.readLine();
        }
    }
}
