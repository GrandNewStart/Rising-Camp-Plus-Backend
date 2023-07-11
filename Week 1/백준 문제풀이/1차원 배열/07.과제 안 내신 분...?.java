// https://www.acmicpc.net/problem/5597
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Boolean> list = new Vector<>();
        for (int i=0; i<30; i++) list.add(false);
        for (int i=0; i<28; i++) {
            int n = scanner.nextInt();
            list.set(n-1, true);
        }
        for (int i=0; i<30; i++) {
            if (!list.get(i)) {
                System.out.println(i+1);
            }
        }
    }
}