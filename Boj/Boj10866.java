import java.util.*;

public class Boj10866 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<Integer> deque = new LinkedList<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            int x;
            switch (s) {
                case "push_front" -> {
                    x = sc.nextInt();
                    deque.addFirst(x);
                }
                case "push_back" -> {
                    x = sc.nextInt();
                    deque.addLast(x);
                }
                case "pop_front" -> {
                    try {
                        System.out.println(deque.removeFirst());
                    } catch (NoSuchElementException e) {
                        System.out.println(-1);
                    }
                }
                case "pop_back" -> {
                    try {
                        System.out.println(deque.removeLast());
                    } catch (NoSuchElementException e) {
                        System.out.println(-1);
                    }
                }
                case "size" -> System.out.println(deque.size());
                case "empty" -> {
                    if (deque.size() == 0)
                        System.out.println(1);
                    else
                        System.out.println(0);
                }
                case "front" -> {
                    try {
                        System.out.println(deque.getFirst());
                    } catch (NoSuchElementException e) {
                        System.out.println(-1);
                    }
                }
                case "back" -> {
                    try {
                        System.out.println(deque.getLast());
                    } catch (NoSuchElementException e) {
                        System.out.println(-1);
                    }
                }
            }
        }
    }
}
