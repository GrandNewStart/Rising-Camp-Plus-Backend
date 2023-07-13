// https://www.acmicpc.net/problem/1152

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        String[] array = S.split(" ");
        int count = 0;
        for (int i=0; i<array.length; i++) {
            if (!array[i].isEmpty()) {
                count++;
            }
        }
        System.out.println(count);
    }

}
