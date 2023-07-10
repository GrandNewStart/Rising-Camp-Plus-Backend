// https://www.acmicpc.net/problem/15552

import java.io.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            int T = Integer.parseInt(bf.readLine());
            for (int i=0; i<T; i++) {
                String line = bf.readLine();
                String[] array = line.split(" ");
                int A = Integer.parseInt(array[0]);
                int B = Integer.parseInt(array[1]);
                String s = Integer.toString(A+B);
                bw.write(s+"\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException error) {

        }
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int sum = 0;
        if (a == b && b == c) {
            sum += (1000*a + 10000);
        }
        if (a == b && a != c) {
            sum += (100 * a + 1000);
        }
        if (a == c && a != b) {
            sum += (100 * a + 1000);
        }
        if (b == c && a != b) {
            sum += (100 * b + 1000);
        }
        if (a != b && b != c && a != c) {
            sum += 100 * Math.max(a, Math.max(b, c));
        }
        System.out.println(sum);
    }
}