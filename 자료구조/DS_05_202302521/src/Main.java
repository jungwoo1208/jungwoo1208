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
public class Main {
    public static void main(String[]args)throws Exception{
        int size=50;
        char c;
        String s ="I go to school";
        ArrayStack<Character> A= new ArrayStack<>(size);
        for(int i=0;i<s.length();i++){
            c=s.charAt(i);
            if(c!=' '){
                A.push(c);
            }else {
                while (true){
                    if(A.isEmpty()){
                        break;
                    }
                    c=A.pop();
                    System.out.print(c);
                }
                System.out.print(" ");
            }
        }
        while (true){
            if(A.isEmpty()){
                break;
            }
            c=A.pop();
            System.out.print(c);
        }
    }
}
