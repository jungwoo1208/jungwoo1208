import java.util.*;

public class Boj13398 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = arr[0];
        right[n - 1] = arr[n - 1];

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(arr[i], left[i - 1] + arr[i]);
            right[n - 1 - i] = Math.max(arr[n - 1 - i], right[n - i] + arr[n - 1 - i]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // The case when there's no deleted element.
            max = Math.max(max, left[i]);
            // The case when the deleted element is not a border of the array.
            if (i > 0 && i < n - 1) {
                max = Math.max(max, left[i - 1] + right[i + 1]);
            }
        }

        System.out.println(max);
    }
}
