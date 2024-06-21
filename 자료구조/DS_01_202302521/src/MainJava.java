import java.io.*;
import java.util.StringTokenizer;

public class MainJava {
    static int []arr;
//    static int []arr2;
    static void print_pathA(int n){
        System.out.print(n+" ");
        int next=arr[n];
        if(next==0){
            System.out.println(0);
            return;
        }
        print_pathA(next);
    }
    static void print_pathB(int n){
        int next=0;
        System.out.print(n+" ");
        for(int i=0;i<arr.length;i++){
            if(arr[i]==n){
                next=i;
                break;
            }
        }
        if(next==0){
            System.out.print(0);
            return;
        }
        print_pathB(next);
    }
    public static void main(String[]args)throws Exception{
        BufferedReader br=new BufferedReader(new FileReader(new File("C:\\Users\\gjw19\\IdeaProjects\\DS_01_202302521\\Week01_input.txt")));
        String s=br.readLine();
        while(s!=null){
            StringTokenizer st=new StringTokenizer(s);
            int n= st.countTokens();
            arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]= Integer.parseInt(st.nextToken());
            }
            print_pathA(0);
            print_pathB(0);
            s=br.readLine();
        }
    }
}
