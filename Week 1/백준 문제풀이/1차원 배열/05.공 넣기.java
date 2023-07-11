// https://www.acmicpc.net/problem/10810
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        List<Integer> list = new Vector<>();
        for (int i=0; i<N; i++) list.add(0);
        for (int a=0; a<M; a++) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            int k = scanner.nextInt();
            for (int b=i-1; b<j; b++) {
                list.set(b, k);
            }
        }
        for (int i=0; i<N; i++) System.out.print(list.get(i) + " ");
    }
}