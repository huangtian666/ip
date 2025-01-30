import java.io.*;
import java.util.ArrayList;

class Storage {
    private static final String FILE_PATH = "./data/talkgpt.txt";

    public static void saveTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH); //create abstract for the file
        File parentDir = file.getParentFile();
        if (parentDir != null) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            //System.out.println("No saved tasks found. Starting fresh.");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}