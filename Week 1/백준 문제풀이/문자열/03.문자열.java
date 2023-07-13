// https://www.acmicpc.net/problem/9086

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i=0; i<T; i++) {
            String S = scanner.next();
            char[] array = S.toCharArray();
            System.out.printf("%c%c\n", array[0], array[array.length-1]);
        }
    }

}
