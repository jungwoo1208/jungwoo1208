import java.util.*;
public class boj1940 {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int []arr = new int [a];
        for(int i = 0; i < a; i++)
            arr[i] = sc.nextInt();
        Arrays.sort(arr);
        int count = 0;
        int start = 0;
        int end = a-1;
        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum == b) {
                arr[start]=0;
                count++;
                start=0;
                end--;
            } else if (sum < b) {
                start++;
            } else {
                start=0;
                end--;
            }
        }
        System.out.println(count);
    }
}
