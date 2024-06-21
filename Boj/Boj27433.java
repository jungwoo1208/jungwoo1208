import java.util.*;
public class Boj27433 {
    static long fac(long n){
        if(n==1||n==0)
            return 1;
        return n * fac(n - 1);
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n= sc.nextInt();
        System.out.println(fac(n));
    }
}
