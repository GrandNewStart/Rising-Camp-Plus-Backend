// https://www.acmicpc.net/problem/10871

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int X = scanner.nextInt();
        for (int i=0; i<N; i++) {
            int x = scanner.nextInt();
            if (x < X) {
                System.out.print(x + " ");
            }
        }

    }
}