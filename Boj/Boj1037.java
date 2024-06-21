import java.util.*;
public class Boj1037 {
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        if(n==1)
            System.out.println((int)Math.pow(sc.nextInt(),2));
        else{
            int[]arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]= sc.nextInt();
            }
            Arrays.sort(arr);
            System.out.println(arr[0]*arr[n-1]);
        }
    }
}
