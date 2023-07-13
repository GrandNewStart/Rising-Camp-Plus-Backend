// https://www.acmicpc.net/problem/11720

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        String S = scanner.next();
        int sum = 0;
        for (int i=0; i<N; i++) {
            char c = S.toCharArray()[i];
            sum += Integer.parseInt(String.valueOf(c));
        }
        System.out.println(sum);
    }

}
