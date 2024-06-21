import java.util.*;
import java.io.*;
public class Boj1927 {
    public static void main(String[]args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int n= Integer.parseInt(br.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i=0;i<n;i++){
            int k= Integer.parseInt(br.readLine());
            if(k==0){
                if(priorityQueue.isEmpty()){
                    bw.write(0+"\n");
                }else {
                    bw.write(priorityQueue.poll()+"\n");
                }
            }else {
                priorityQueue.add(k);
            }
        }
        bw.flush();
        bw.close();
    }
}
