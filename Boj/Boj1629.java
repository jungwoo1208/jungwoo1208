import java.io.*;
import java.util.*;
public class Boj1629 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStr = br.readLine().split(" ");
        long A = Long.parseLong(inputStr[0]);
        long B = Long.parseLong(inputStr[1]);
        long C = Long.parseLong(inputStr[2]);
        for(long i=0;i<B;i++){
            A = (A%C);
            A*=A;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(A%C));
        bw.flush();
        bw.close();
    }
}
