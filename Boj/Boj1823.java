import java.util.*;
public class Boj1823 {
    static void star (int n, char[][] arr){

    }

    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        char [][]arr=new char [n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j]='*';
            }
        }
        sc.close();
    }
}
