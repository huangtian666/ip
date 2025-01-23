import java.util.ArrayList;
import java.util.Scanner;

public class TalkGPT {

    private static final int INDEX_OFFSET = 1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        String name = "TalkGPT";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");

        while (true) {
            String request = sc.nextLine().trim().toLowerCase();
            String [] requestArray = request.split(" ");

            if (request.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("You have no task yet!");
                } else {
                    System.out.println("Your ToDo List is here!");
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                }
            } else if (request.equals("bye")) {
                break;
            } else if (requestArray[0].equals("mark") || requestArray[0].equals("unmark")) {
                boolean newStatus;
                if (requestArray[0].equals("mark")) {
                    System.out.println("Good Job on completing your task! I've marked this task!");
                    newStatus = true;
                } else {
                    System.out.println("I've unmarked your task!");
                    newStatus = false;
                }
                int taskId = Integer.parseInt(requestArray[1]);
                Task updatedTask = new Task(taskId,
                        tasks.get(taskId - INDEX_OFFSET).getDescription(), newStatus);
                tasks.set(taskId - INDEX_OFFSET, updatedTask);
                System.out.println(updatedTask);
            } else {
                tasks.add(new Task(tasks.size() + INDEX_OFFSET, request));
                System.out.println("added: " +  request);
            }
        }

        System.out.println("Goodbye! See you next time!");
    }
}
