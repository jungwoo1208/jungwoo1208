import java.util.*;

public class Boj9012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        fun f =new fun();
        for(int i=0;i<n;i++){
            String s=sc.next();
            f.a(s);
        }
    }
}
class fun{
    void a(String s){
        int sum = 0;
        boolean result = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                sum++;
            else if (s.charAt(i) == ')') {
                if (sum == 0) {
                    result = false;
                    break;
                } else
                    sum--;
            }
        }
        if (sum != 0)
            result = false;
        if (result)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}