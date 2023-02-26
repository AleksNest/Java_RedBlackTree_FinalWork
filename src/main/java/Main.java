import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        final RedBlackTree tree = new RedBlackTree();
        BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));

        while (true) {
            try {
                System.out.println("input value");
                int value = Integer.parseInt((reader.readLine()));
                tree.add(value);
                System.out.println("the end");
            } catch (Exception ignored) {

            }
        }

    }

}
