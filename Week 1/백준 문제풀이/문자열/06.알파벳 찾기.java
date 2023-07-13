// https://www.acmicpc.net/problem/10809

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.next();
        char[] array = S.toCharArray();
        List<Integer> positions = new ArrayList<>();
        for (int i=0; i<26; i++) positions.add(-1);
        for (int i=0; i<S.length(); i++) {
            int ascii = array[i] - 97;
            if (positions.get(ascii) == -1) {
                positions.set(ascii, i);
            }
        }
        for (int i=0; i<26; i++) System.out.printf("%d ", positions.get(i));
    }

}
