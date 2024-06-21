import java.util.*;
public class Boj10250 {
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        for(int i=0;i<n;i++){
            int a= sc.nextInt();
            int b= sc.nextInt();
            int c= sc.nextInt();
            int k=c%a;
            int j=c/a;
            if(k==0){
                System.out.println(a*100+j);
            }else {
                System.out.println(100 * k + j + 1);
            }
        }
    }
}
