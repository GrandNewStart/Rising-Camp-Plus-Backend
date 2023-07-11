// https://www.acmicpc.net/problem/1546

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Double> list = new Vector<>();
        double max = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            double n = scanner.nextInt();
            list.add(n);
            if (n > max) {
                max = n;
            }
        }
        double sum = 0;
        for (int i=0; i<N; i++) {
            double score = list.get(i)/max*100;
            sum += score;
        }
        double avg = sum/N;
        System.out.println(avg);
    }

}