// https://www.acmicpc.net/problem/10811

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new Vector<>();
        int N = scanner.nextInt();
        for (int a=0; a<N; a++) list.add(a+1);
        int M = scanner.nextInt();
        for (int a=0; a<M; a++) {
            int i = scanner.nextInt()-1;
            int j = scanner.nextInt()-1;
            if (i == j) continue;
            for (int b=0; b<(j-i); b++) {
                int left = i+b;
                int right = j-b;
                if (left >= right) break;
                int temp = list.get(right);
                list.set(right, list.get(left));
                list.set(left, temp);
            }
        }
        for (int a=0; a<N; a++) {
            System.out.print(list.get(a)+" ");
        }
    }

}
}