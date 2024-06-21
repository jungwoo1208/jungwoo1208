import java.util.*;
public class Boj2775 {
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        for(int i=0;i<n;i++){
            int y=sc.nextInt();
            int x= sc.nextInt();
            int []arr=new int[x];
            for(int j=0;j<x;j++){
                arr[j]= j+1;
            }
            for(int j=1;j<=y;j++){
                int sum=0;
                for(int k=0;k<x;k++){
                    sum+=arr[k];
                    arr[k]=sum;
                }
            }
            System.out.println(arr[x-1]);
        }
    }
}
