import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj2162 {
    static int n;
    static ArrayList<line>arr=new ArrayList<>();
    static class line{
        int x1,x2,y1,y2;
        public line(int x1,int y1,int x2,int y2){
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
        }
    }
    boolean test(line l1,line l2){

        return false;
    }
    public static void main(String[]args)throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        n= Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int x1= Integer.parseInt(st.nextToken());
            int y1= Integer.parseInt(st.nextToken());
            int x2= Integer.parseInt(st.nextToken());
            int y2= Integer.parseInt(st.nextToken());
            arr.add(new line(x1,y1,x2,y2));
        }
    }
}
