package talkgpt.storage;

import talkgpt.task.*;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    //private static final String FILE_PATH = "./data/talkgpt.txt";

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        File file = new File(this.filePath); //create abstract for the file
        File parentDir = file.getParentFile();
        if (parentDir != null) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
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