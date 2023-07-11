// https://www.acmicpc.net/problem/2562

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i=0; i<9; i++) {
            int x = scanner.nextInt();
            if (x > max) {
                max = x;
                index = i;
            }
        }
        System.out.printf("%d %d", max, index+1);
    }
}