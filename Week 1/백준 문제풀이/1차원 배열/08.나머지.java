// https://www.acmicpc.net/problem/3052
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Boolean> list = new Vector<>();
        for (int i=0; i<42; i++) list.add(false);
        for (int i=0; i<10; i++) {
            int n = scanner.nextInt();
            list.set(n%42, true);
        }
        int count = 0;
        for (int i=0; i<42; i++) {
            if (list.get(i)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
