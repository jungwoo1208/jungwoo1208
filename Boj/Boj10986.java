import java.util.*;
import java.io.*;
public class Boj10986 {
    public static void main(String[]args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n= Integer.parseInt(st.nextToken());
        int k= Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        long []arr=new long[n];
        long []arr2=new long[k];
        arr[0]= Integer.parseInt(st.nextToken());
        long count=0;
        for(int i=1;i<n;i++)
            arr[i]= arr[i-1]+Integer.parseInt(st.nextToken());
        for(int i=0;i<n;i++){
            int re=(int)(arr[i]%k);
            if(re==0)count++;
            arr2[re]++;
        }
        for(int i=0;i<k;i++){
            if(arr2[i]>1){
                count+=(arr2[i]*(arr2[i]-1))/2;
            }
        }
        System.out.println(count);
    }
}
