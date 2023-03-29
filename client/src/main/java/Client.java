import java.util.Scanner;

/**
 * Client entry point class. Contains <code>main()</code> method.
 */

public class Client {

    /**
     * Client entry point.
     *
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        System.out.println("\u001B[33mPerson database" +
                " console application started\u001B[0m");

        System.out.print("\nEnter path to the file\n$ ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
    }
}