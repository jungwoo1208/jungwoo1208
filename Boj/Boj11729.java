
import java.util.*;
public class Boj11729 {
    static int count=0;
    static StringBuilder sb=new StringBuilder();
    public static void hanoi(int n,int from,int by,int to)throws Exception{
        if(n==1){
            sb.append("\n"+from+" "+to);
            count++;
        }
        else{
            hanoi(n-1,from,to,by);
            sb.append("\n"+from+" "+to);
            count++;
            hanoi(n-1,by,from,to);
        }
    }
    public static void main(String[]args)throws Exception{
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        hanoi(n,1,2,3);
        System.out.print(count);
        System.out.println(sb);
    }
}
