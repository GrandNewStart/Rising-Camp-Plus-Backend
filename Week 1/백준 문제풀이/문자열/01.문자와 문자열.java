// https://www.acmicpc.net/problem/27866

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        int i = scanner.nextInt();
        System.out.println(S.toCharArray()[i-1]);
    }

}