import java.util.*;

public class Boj2525 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hour = sc.nextInt();
        int minute = sc.nextInt();
        int time = sc.nextInt();
        int newHour = (hour + (minute + time) / 60) % 24;
        int newMinute = (minute + time) % 60;
        System.out.println(newHour + " " + newMinute);
        sc.close();
    }
}
