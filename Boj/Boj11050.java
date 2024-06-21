import java.util.*;
public class Boj11050 {
    static int fac(int n) {
        int result = 1;
        if (n == 0 || n == 1)
            return result;
        return n * fac(n - 1);
    }
    static int res(int x,int y,int z){
            return x/(y*z);
        }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        int k= sc.nextInt();
        int x=fac(n);
        int y=fac(k);
        int z=fac(n-k);
        System.out.println(res(x,y,z));

    }
}

