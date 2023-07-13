// https://www.acmicpc.net/problem/2908

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String A = scanner.next();
        String B = scanner.next();
        StringBuilder a = new StringBuilder();
        for (int i=A.length()-1; i>=0; i--) {
            char c = A.toCharArray()[i];
            a.append(c);
        }
        StringBuilder b = new StringBuilder();
        for (int i=B.length()-1; i>=0; i--) {
            char c = B.toCharArray()[i];
            b.append(c);
        }
        int intA = Integer.parseInt(a.toString());
        int intB = Integer.parseInt(b.toString());
        System.out.println(Math.max(intA, intB));
    }

}
