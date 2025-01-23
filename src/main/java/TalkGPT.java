import java.util.Scanner;

public class TalkGPT {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String name = "TalkGPT";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");

        while (true) {
            String request = sc.nextLine();
            if (request.toLowerCase().equals("bye")) {
                break;
            }
        }

        System.out.println("Goodbye! See you next time!");
    }
}
