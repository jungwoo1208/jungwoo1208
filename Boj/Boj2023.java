import java.util.ArrayList;
import java.util.Scanner;
public class Boj2023 {
    static int n;
    static int []arr={2,3,5,7};
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        n= sc.nextInt();
        for(int i:arr){
            dfs(i,1);
        }
    }
    static void dfs(int a,int b){
        if(b==n){
            if(isPrime(a)) {
                System.out.println(a);
            }
            return;
        }
        for(int i=1;i<10;i++){
            if(i%2==0)
                continue;
            if(isPrime(a*10+i))
                dfs(a*10+i,b+1);
        }
    }

    static boolean isPrime(int a) {
        for(int i=2;i<=a/2;i++){
            if(a%i==0){
                return false;
            }
        }
        return true;
    }
}
