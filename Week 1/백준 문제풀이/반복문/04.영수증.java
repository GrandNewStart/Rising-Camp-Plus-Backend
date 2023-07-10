// https://www.acmicpc.net/problem/25304

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int X = scanner.nextInt();
        int N = scanner.nextInt();
        int sum = 0;
        for (int i=0; i<N; i++) {
            int price = scanner.nextInt();
            int count = scanner.nextInt();
            sum += price * count;
        }
        System.out.println(sum == X ? "Yes" : "No");
    }
}