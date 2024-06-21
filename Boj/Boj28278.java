import java.util.*;
import java.io.*;

public class Boj28278 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < n; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            switch (k) {
                case 1:
                    int numToAdd = Integer.parseInt(st.nextToken());
                    stack.push(numToAdd);
                    break;
                case 2:
                    if (stack.isEmpty()) {
                        bw.write("-1");
                    } else {
                        bw.write(stack.pop().toString());
                    }
                    bw.newLine();
                    break;
                case 3:
                    bw.write(Integer.toString(stack.size()));
                    bw.newLine();
                    break;
                case 4:
                    if (stack.isEmpty()) {
                        bw.write("1");
                    } else {
                        bw.write("0");
                    }
                    bw.newLine();
                    break;
                case 5:
                    if (stack.isEmpty()) {
                        bw.write("-1");
                    } else {
                        bw.write(stack.peek().toString());
                    }
                    bw.newLine();
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}
