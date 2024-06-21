import java.util.*;
public class Boj11659 {
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int a= sc.nextInt();
        int b= sc.nextInt();
        int []arr=new int [a+1];
        arr[0]= 0;
        for(int i=0;i<a;i++)
            arr[i+1]=arr[i]+ sc.nextInt();
        for(int i=0;i<b;i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println(arr[y] - arr[x-1]);
        }
    }
}
