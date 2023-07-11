// https://www.acmicpc.net/problem/10818

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
            int x = scanner.nextInt();
            if (x < min) {
                min = x;
            }
            if (x > max) {
                max = x;
            }
        }
        System.out.printf("%d %d", min, max);
    }
}