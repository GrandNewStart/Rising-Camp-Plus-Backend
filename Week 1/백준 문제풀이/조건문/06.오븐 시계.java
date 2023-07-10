// https://www.acmicpc.net/problem/2525

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int H = scanner.nextInt();
        int M = scanner.nextInt();
        int time = scanner.nextInt();
        M += time;
        H += M/60;
        M %= 60;
        if (H >= 24) {
            H -= 24;
        }
        System.out.printf("%d %d", H, M);
    }

}