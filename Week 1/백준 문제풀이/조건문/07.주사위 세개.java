// https://www.acmicpc.net/problem/2480

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int sum = 0;
        if (a == b && b == c) {
            sum += (1000*a + 10000);
        }
        if (a == b && a != c) {
            sum += (100 * a + 1000);
        }
        if (a == c && a != b) {
            sum += (100 * a + 1000);
        }
        if (b == c && a != b) {
            sum += (100 * b + 1000);
        }
        if (a != b && b != c && a != c) {
            sum += 100 * Math.max(a, Math.max(b, c));
        }
        System.out.println(sum);
    }
}