// https://www.acmicpc.net/problem/10813

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        List<Integer> list = new Vector<>();
        for (int i=0; i<N; i++) list.add(i+1);
        for (int i=0; i<M; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int temp = list.get(a-1);
            list.set(a-1, list.get(b-1));
            list.set(b-1, temp);
        }
        for (int i=0; i<N; i++) System.out.print(list.get(i) + " ");
    }
}