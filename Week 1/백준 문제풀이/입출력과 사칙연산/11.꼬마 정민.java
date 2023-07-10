// https://www.acmicpc.net/problem/11382

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger sum = new BigInteger("0");
        BigInteger A = scanner.nextBigInteger();
        sum = sum.add(A);
        BigInteger B = scanner.nextBigInteger();
        sum = sum.add(B);
        BigInteger C = scanner.nextBigInteger();
        sum = sum.add(C);
        System.out.println(sum);
        scanner.close();
    }
}