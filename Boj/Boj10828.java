import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj10828 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> st = new Stack<Integer>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            if (tmp[0].equals("push")) {
                st.push(Integer.parseInt(tmp[1]));
            } else if (tmp[0].equals("pop")) {
                if (st.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(st.pop());
            } else if (tmp[0].equals("size")) {
                System.out.println(st.size());
            } else if (tmp[0].equals("empty")) {
                if (st.isEmpty())
                    System.out.println(1);
                else
                    System.out.println(0);
            } else if (tmp[0].equals("top")) {
                if (st.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(st.peek());
            }
        }

    }
}
