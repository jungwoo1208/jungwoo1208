import java.util.Scanner;

public class Boj3910 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k;
        int power = 0;
        int N = 473;
        k = 1;

        while (true) {
            if (k > N) {
                break;
            }
            k *= 2;
            power++;
        }

        System.out.println(k + "," + power);
        if (N - Math.pow(2, power - 1) < Math.pow(2, power) - N)
            System.out.println(power - 1);
        else
            System.out.println(power);

        if (N < k) {
            // Division
        } else {
            // Exponentiation
        }

        scanner.close();
    }
}
