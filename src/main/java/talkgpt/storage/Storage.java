package talkgpt.storage;

import talkgpt.task.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages file storage for task data.
 * <p>
 * This class handles saving and loading tasks from a file. It ensures that tasks
 * persist across program runs by writing them to and reading them from disk.
 * </p>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a {@code Storage} instance with a specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the storage file.
     * <p>
     * This method writes each task to a file in a structured format defined
     * by the {@code toFileFormat()} method in the {@code Task} class.
     * If the file's parent directory does not exist, it is created.
     * </p>
     *
     * @param tasks The list of tasks to be saved.
     */
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

    /**
     * Loads tasks from the storage file.
     * <p>
     * Reads the file line by line and reconstructs task objects using
     * {@code Task.fromFileFormat()}. If the file does not exist, an empty list is returned.
     * </p>
     *
     * @return An {@code ArrayList} containing the loaded tasks.
     */
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