import java.util.*;
public class Boj2018 {
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        int count=1;
        int start=1;
        int end=1;
        int sum=1;
        while(end!=n){
            if(sum==n){
                count++;
                end++;
                sum=sum+end;
            } else if (sum>n) {
                sum=sum-start;
                start++;
            }else {
                end++;
                sum=sum+end;
            }
        }
        System.out.println(count);
    }
}
