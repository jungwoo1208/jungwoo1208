import java.util.*;
public class Boj10870 {
    static long fib(int n){
        if(n==0)
            return 0;
        if (n==1 || n==2)
        return 1;
    else
        return fib(n-1) + fib(n-2);
    }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        System.out.println(fib(n));
    }
}