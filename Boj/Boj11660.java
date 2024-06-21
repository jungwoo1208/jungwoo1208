import java.io.*;
import java.util.StringTokenizer;

public class Boj11660 {
    public static void main(String[]args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int test=Integer.parseInt(st.nextToken());
        int [][]arr=new int[n][n+1];
        for(int i=0;i<n;i++){
            StringTokenizer st1=new StringTokenizer(br.readLine());
            arr[i][0]=0;
            for(int j=0;j<n;j++){
                arr[i][j+1]=arr[i][j]+Integer.parseInt(st1.nextToken());
            }
        }
        for(int i=0;i<test;i++){
            StringTokenizer st2=new StringTokenizer(br.readLine());
            int x1= Integer.parseInt(st2.nextToken());
            int y1= Integer.parseInt(st2.nextToken());
            int x2= Integer.parseInt(st2.nextToken());
            int y2= Integer.parseInt(st2.nextToken());
            int sum=0;
            for(int j=y1-1;j<y2;j++){
                sum+=arr[j][x2]-arr[j][x1-1];
            }
            System.out.println(sum);
        }
    }
}
