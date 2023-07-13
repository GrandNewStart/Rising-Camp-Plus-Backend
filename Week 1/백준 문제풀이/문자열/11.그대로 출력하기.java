// https://www.acmicpc.net/problem/10807

import java.util.List;
import java.util.Scanner;
import java.util.Vector;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Integer> array = new Vector();
        for (int i=0; i<N; i++) {
            int x = scanner.nextInt();
            array.add(x);
        }
        int v = scanner.nextInt();
        int count = 0;
        for (int item : array) {
            if (item == v) {
                count++;
            }
        }
        System.out.println(count);
    }
}