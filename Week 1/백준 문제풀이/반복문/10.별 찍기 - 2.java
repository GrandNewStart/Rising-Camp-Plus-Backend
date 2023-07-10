// https://www.acmicpc.net/problem/2480
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i=0; i<N; i++) {
            for (int j=0; j<N-i-1; j++) {
                System.out.print(" ");
            }
            for (int j=i; j>=0; j--) {
                System.out.print("*");
            }
            if (i != N-1) {
                System.out.print("\n");
            }
        }
    }
}