import java.util.*;
public class Boj4948 {
    static void primeNumberSieve(int k,int n){
        int number = n;
        int []primeNum=new int[n+1];
        for (int i = 2; i <= number; i++)
        {
            primeNum[i] = i;
        }
        for (int i = 2; i <= Math.sqrt(number); i++)
        {
            if (primeNum[i] == 0)
                continue;
            for (int j = i * i; j <= number; j += i)
                primeNum[j] = 0;
        }
        int sum=0;
        for(int i=k;i<=n;i++){
            if(primeNum[i]!=0)
                sum++;
        }
        System.out.println(sum);
    }

    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        while(n!=0){
            int n2=2*n;
            primeNumberSieve(n+1,n2);
            n= sc.nextInt();
        }
    }
}
