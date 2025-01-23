import java.util.ArrayList;
import java.util.Scanner;

public class TalkGPT {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<ToDos> tasks = new ArrayList<ToDos>();
        String name = "TalkGPT";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");

        while (true) {
            String request = sc.nextLine().trim().toLowerCase();

            if (request.equals("list")) {

                if (tasks.isEmpty()) {
                    System.out.println("You have no task yet!");
                } else {
                    System.out.println("Your ToDo List is here!");
                    for (ToDos task : tasks) {
                        System.out.println(task);
                    }
                }
            } else if (request.equals("bye")) {
                break;
            } else {
                tasks.add(new ToDos(tasks.size() + 1, request));
                System.out.println("added: " +  request);
            }
        }

        System.out.println("Goodbye! See you next time!");
    }
}
