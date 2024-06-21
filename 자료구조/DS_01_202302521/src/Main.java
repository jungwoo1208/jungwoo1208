import java.util.Stack;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Stack <Character> st = new Stack <>();

        for(int i=0; i<t; i++) {
            String s = sc.next();

            for(int j=0; j<s.length(); j++) {
                char ch = s.charAt(j);

                if(ch=='(')
                    st.push(ch);
                else {
                    if(st.empty()) {
                        st.push(ch);
                        break;
                    }
                    else {
                        st.pop();
                    }
                }
            }

            if(st.empty())
                System.out.println("Yes");
            else
                System.out.println("No");
            st.clear();
        }
        sc.close();
    }
}