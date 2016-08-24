import java.util.*;

/**
 * Created by jecyhw on 16-8-23.
 */
public class Hello {
    @org.junit.Test
    public void test() {
        int n;
        Scanner in = new Scanner(System.in);
        n = 50;
        int end = n;
        Queue<Integer> q = new LinkedList<>();
        for (int i = n; i > 0; i--) {
            q.add(i);
            q.add(q.remove());
        }

        Stack<Integer> s = new Stack<>();

        Queue<Integer> q2 = new LinkedList<>();
        while (!q.isEmpty()) {
            s.push(q.peek());
            System.out.print(q.remove() + " ");
        }

        while (!s.isEmpty()) {
            q2.add(s.pop());
        }

        System.out.println();
        while (!q2.isEmpty()) {
            q2.add(q2.remove());
            System.out.print(q2.remove() + " ");
        }
    }
}
