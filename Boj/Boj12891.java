import java.util.*;
import java.io.*;
public class Boj12891 {
    public static boolean test(int[] test, int[] count){
        boolean k= test[0] <= count[0];
        if(test[1]>count[1]){
            k=false;
        }if(test[2]>count[2]){
            k=false;
        }if(test[3]>count[3]){
            k=false;
        }
        return k;
    }
    public static void main(String[]args)throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int s=Integer.parseInt(st.nextToken());
        int n= Integer.parseInt(st.nextToken());
        char[] arr=new char[s];
        int result=0;
        String k=br.readLine();
        for(int i=0;i<s;i++){
            arr[i]=k.charAt(i);
        }
        st= new StringTokenizer(br.readLine());
        int [] test=new int[4];
        test[0]= Integer.parseInt(st.nextToken());
        test[1]= Integer.parseInt(st.nextToken());
        test[2]= Integer.parseInt(st.nextToken());
        test[3]= Integer.parseInt(st.nextToken());
        int []count=new int[4];
        for(int i=0;i<n;i++){
            if(arr[i]=='A')
                count[0]++;
            else if (arr[i]=='C')
                count[1]++;
            else if (arr[i]=='G')
                count[2]++;
            else
                count[3]++;
        }
        if(test(test,count)){
            result++;
        }
        for(int i=0;i<s-n;i++){
            if(arr[i]=='A')
                count[0]--;
            else if (arr[i]=='C')
                count[1]--;
            else if (arr[i]=='G')
                count[2]--;
            else
                count[3]--;
            if(arr[i+n]=='A')
                count[0]++;
            else if (arr[i+n]=='C')
                count[1]++;
            else if (arr[i+n]=='G')
                count[2]++;
            else
                count[3]++;
            if(test(test,count)){
                result++;
            }
        }
        System.out.println(result);
    }
}
