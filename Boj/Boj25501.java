import java.util.*;
public class Boj25501 {
    public static int recursion(String s, int l, int r){
        if(l >= r) return 1;
        else if(s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l+1, r-1);
    }
    public static int isPalindrome(String s){
        return recursion(s, 0, s.length()-1);
    }
    public static int sum(String s){
        return sum(s,0,s.length()-1,0);
    }
    public static int sum(String s, int l,int r,int sum){
        if(l >= r) return ++sum;
        else if(s.charAt(l) != s.charAt(r)) return ++sum;
        else return sum(s, l+1, r-1,++sum);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        for(int i=0;i<n;i++){
            String s= sc.next();
            System.out.println(isPalindrome(s)+" "+ sum(s));
        }
    }
}
