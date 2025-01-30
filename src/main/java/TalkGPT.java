import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TalkGPT {
    private final Storage storage;
    private final TaskList tasks;
    private final UI ui;

    public TalkGPT(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError();
            loadedTasks = new TaskList();
        }
        this.tasks = loadedTasks;
    }

    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.getUserInput();
            Command c = Parser.parse(input, this.ui);
            isExit = c.execute(this.tasks, this.storage, this.ui);
        }
    }



    public static void main(String[] args) {
        new TalkGPT("./data/tasks.txt").run();
    }

    /*public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = Storage.loadTasks();
        String name = "TalkGPT";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");

        while (true) {
            String request = sc.nextLine().trim().toLowerCase();

            if (request.equals("list")) {
                listTasks(tasks);
            } else if (request.equals("bye")) {
                System.out.println("Goodbye! See you next time!");
                break;
            } else if (request.startsWith("mark") || request.startsWith("unmark")) {
                handleMark(tasks, request);
            } else if (request.startsWith("delete")) {
                deleteTask(tasks, request);
            } else if(request.isEmpty()) {
                System.out.println("Your command cannot be empty :(");
            } else if (request.equals("help")) {
                printHelp();
            } else if (request.equals("clear")) {
                clearTasks(tasks);
            } else if (request.startsWith("list on")) {
                listTasksDueOnDate(tasks, request);
            } else {
                addTask(tasks, request);//add task
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

    private static void deleteTask(ArrayList<Task> tasks, String request) {
        String [] requestArray = request.split(" ");
        if (requestArray.length < 2) {
            System.out.println("Please enter a task index");
        }

        int taskId = Integer.parseInt(requestArray[1]);

        if (tasks.isEmpty()) {
            System.out.println(NO_TASK);
        } else {
            System.out.println("Your task has been deleted :)");
            System.out.println(tasks.get(taskId - INDEX_OFFSET));
            tasks.remove(taskId - INDEX_OFFSET);
            for (int i = taskId - INDEX_OFFSET; i < tasks.size(); i++) {
                tasks.get(i).setId(i + INDEX_OFFSET);
            }
            System.out.printf("You have %s tasks in your ToDo List now!%n", tasks.size());
            Storage.saveTasks(tasks); // Save the updated task list
        }
    }

    private static void addTask(ArrayList<Task> tasks, String request) {
        Task newTask;
        if (request.startsWith("todo")) {
            newTask = createTodoTask(tasks, request);
        } else if (request.startsWith("deadline")) {
            newTask = createDeadlineTask(tasks, request);
        } else {
            newTask = createEventTask(tasks, request);
        }
        if (newTask != null && newTask.isValid()) {
            boolean isDuplicate = tasks.stream()
                    .anyMatch(task -> task.getDescription().equals(newTask.getDescription()));
            if (!isDuplicate) {
                tasks.add(newTask);
                Storage.saveTasks(tasks);
                System.out.println(newTask);
            } else {
                System.out.println("Task already exists: " + newTask);
            }
        }
    }

    private static Task createTodoTask(ArrayList<Task> tasks, String request) {
        if (request.length() <= 5) {
            System.out.println("Please enter a task name/description");
            return null;
        }
        String description = request.substring(5);
        return new ToDos(tasks.size() + INDEX_OFFSET, description);
    }

    private static Task createDeadlineTask(ArrayList<Task> tasks, String request) {
        if (!request.contains(" /by ")) {
            System.out.println(DEADLINE_REMINDER);
            return null;
        }
        String[] requestBreakDown = request.split(" /by ");
        String description = requestBreakDown[0].substring(9);
        return new Deadline(tasks.size() + INDEX_OFFSET, description, requestBreakDown[1]);
    }

    private static Task createEventTask(ArrayList<Task> tasks, String request) {
        if (!request.contains(" /from ") || !request.contains(" /to ")) {
            System.out.println(EVENT_REMINDER);
            return null;
        }
        String[] duration = request.split(" /from ");
        String description = duration[0].substring(6);
        duration = duration[1].split(" /to ");
        return new Event(tasks.size() + INDEX_OFFSET, description, duration[0], duration[1]);
    }

    private static void handleMark(ArrayList<Task> tasks, String request) {
        String [] requestArray = request.split(" ");
        int taskId = Integer.parseInt(requestArray[1]);
        if (taskId < 1 || taskId > tasks.size()) {
            System.out.println("Please enter a valid task index");
        } else {
            Task oldTask = tasks.get(taskId - INDEX_OFFSET);
            Task updatedTask = oldTask.toggleStatus();
            tasks.set(taskId - INDEX_OFFSET, updatedTask);
            Storage.saveTasks(tasks);
            System.out.println(updatedTask);
        }
    }

    private static void clearTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(NO_TASK);
        } else {
            tasks.clear();
            Storage.saveTasks(tasks); // Save the empty list to the file
            System.out.println("All tasks have been cleared. You now have 0 tasks.");
        }
    }

    private static void listTasksDueOnDate(ArrayList<Task> tasks, String request) {
        if (tasks.isEmpty()) {
            System.out.println(NO_TASK);
        } else {
            boolean found = false;
            String dateString = request.substring(8).trim(); // Extract the date string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate filterDate = LocalDate.parse(dateString, formatter);
            System.out.println("Tasks due on " + filterDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            for (Task task : tasks) {
                if(task.isDueOn(filterDate)) {
                    found = true;
                    System.out.println(task);
                }
            }
            if(!found) {
                System.out.println("No task due on this date.");
            }
        }
    }

    private static void printHelp() {
            System.out.println("Available commands:");
            System.out.println("1. list - Display all tasks");
            System.out.println("2. todo <description> - Add a ToDo");
            System.out.println("3. deadline <description> /by <date: dd/mm/yyyy time> - Add a Deadline");
            System.out.println("4. event <description> " +
                    "/from <start: dd/mm/yyyy time> /to <end: dd/mm/yyyy time> - Add an Event");
            System.out.println("5. mark <taskId> - Mark a task as completed");
            System.out.println("6. unmark <taskId> - Unmark a task");
            System.out.println("7. delete <taskId> - Delete a task");
            System.out.println("8. bye - Exit the application");
            System.out.println("9. clear - Clear all tasks");
            System.out.println("10. list on <date> - List tasks due on this date");
            System.out.println("11. help - Print all available commands");
    }

     */
}
