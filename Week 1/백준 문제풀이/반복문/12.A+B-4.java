// https://www.acmicpc.net/problem/10951
import java.io.*;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = br.readLine()) != null ) {
                String[] array = line.split(" ");
                int A = Integer.parseInt(array[0]);
                int B = Integer.parseInt(array[1]);
                System.out.println(A+B);
            }
            br.close();
        } catch (IOException error) {

        }

    }
}