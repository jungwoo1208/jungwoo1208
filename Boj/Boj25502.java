import java.util.*;
import java.io.*;
public class Boj25502 {
    public static void main(String[]args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        long l= Long.parseLong(st.nextToken());
        long n= Long.parseLong(st.nextToken());
        st =new StringTokenizer(br.readLine());
        long[]arr=new long[(int) l];
        for(int i=0;i<l;i++){
            arr[i]= Long.parseLong(st.nextToken());
        }
        long plus= arr[1]-arr[0];
        boolean p=true;
        boolean m=true;
        if(arr[1]-arr[0]<=0)
            p=false;
        if(arr[1]/arr[0]<=0)
            m=false;
        if(p){
            for(int j=0;j<l-1;j++){
                if(arr[j+1]-arr[j]!=plus){
                    p=false;
                    break;
                }
            }
        }
        long multi=arr[1]/arr[0];
        if(m){
            for(int j=0;j<l-1;j++){
                if(arr[j+1]/arr[j]!=multi){
                    m=false;
                    break;
                }
            }
        }
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            long a= Long.parseLong(st.nextToken());
            long b= Long.parseLong(st.nextToken());
            arr[(int) (a-1)]=b;

            if(p){
                System.out.println("+");
            } else if (m) {
                System.out.println("*");
            }else{
                System.out.println("?");
            }
        }
    }
}
