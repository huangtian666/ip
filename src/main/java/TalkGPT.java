import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class TalkGPT {

    private static final int INDEX_OFFSET = 1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "TalkGPT";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");

        while (true) {
            String request = sc.nextLine().trim().toLowerCase();
            String [] requestArray = request.split(" ");

            if (request.equals("list")) {
                listTasks(tasks);
            } else if (request.equals("bye")) {
                System.out.println("Goodbye! See you next time!");
                break;
            } else if (requestArray[0].equals("mark") || requestArray[0].equals("unmark")) {
                int taskId = Integer.parseInt(requestArray[1]);
                Task oldTask = tasks.get(taskId - INDEX_OFFSET);
                Task updatedTask = oldTask.toggleStatus(oldTask);
                tasks.set(taskId - INDEX_OFFSET, updatedTask);
                System.out.println(updatedTask);
            } else if (requestArray[0].equals("delete")) {
                int taskId = Integer.parseInt(requestArray[1]);
                tasks = deleteTask(tasks, taskId);
            } else if(request.isEmpty()) {
                System.out.println("Your command cannot be empty :(");
                continue;
            } else { //add task
                Task newTask;
                if (requestArray[0].equals("todo")) {
                    newTask = new ToDos(tasks.size() + INDEX_OFFSET, request.substring(5));
                } else if (requestArray[0].equals("deadline")) {
                    if (!request.contains(" /by ")) {
                        System.out.println("Sorry, the format you entered is invalid. Please use: deadline <description> /by <date>");
                        continue;
                    } else {
                        String[] requestBreakDown = request.split(" /by ");
                        String description = requestBreakDown[0].substring(9);
                        newTask = new Deadline(tasks.size() + INDEX_OFFSET, description, requestBreakDown[1]);
                    }
                    //need to check if the task if valid
                } else{
                    if (!request.contains(" /from ") || !request.contains(" /to ")) {
                        System.out.println("Sorry, the format you entered is invalid. " +
                                "Please use: event <description> /from <start> /to <end>");
                        continue;
                    } else {
                        String[] duration = request.split(" /from ");
                        String description = duration[0].substring(6);
                        duration = duration[1].split(" /to ");
                        newTask = new Event(tasks.size() + INDEX_OFFSET, description, duration[0], duration[1]);
                    }
                }
                tasks.add(newTask);
                System.out.println(newTask);
            }
        }
    }

    private static void listTasks (ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You have no task yet!");
        } else {
            System.out.println("Your ToDo List is here!");
            for (Task task : tasks) {
                System.out.println(task.getId() + ". " + task);
            }
        }
    }

    private static ArrayList<Task> deleteTask(ArrayList<Task> tasks, int taskId) {
        if (tasks.isEmpty()) {
            System.out.println("You have no task yet!");
        } else {
            System.out.println("Your task has been deleted :)");
            System.out.println(tasks.get(taskId - INDEX_OFFSET));
            tasks.remove(taskId - INDEX_OFFSET);
            System.out.printf("You have %s tasks in your ToDo List now!%n", tasks.size());
        }
        return tasks;
    }
}
