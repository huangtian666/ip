package talkgpt.task;

import talkgpt.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an abstract task.
 * <p>
 * This class defines the base structure for different types of tasks, including
 * properties such as ID, description, and completion status.
 * </p>
 * <p>
 * Concrete task types (e.g., {@code ToDo}, {@code Deadline}, {@code Event}) must
 * implement methods to define their specific behaviors.
 * </p>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */
public abstract class Task {

    private String description;
    private int id;
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with the given ID and description.
     * The task is initially marked as not completed.
     *
     * @param index       The unique ID of the task.
     * @param description The task description.
     */
    public Task(int index, String description) {
        this.id = index;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new {@code Task} with the given ID, description, and completion status.
     *
     * @param index       The unique ID of the task.
     * @param description The task description.
     * @param status      The completion status of the task.
     */
    public Task(int index, String description, boolean status) {
        this.id = index;
        this.description = description;
        this.isDone = status;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

   public  boolean getStatus() {
        return this.isDone;
    }

   public void setId(int id) {
        this.id = id;
    }

    public abstract LocalDateTime getStart();
    public abstract LocalDateTime getEnd();

    /**
     * Marks a task as done.
     * <p>
     * Concrete subclasses must implement this method to define their own completion behavior.
     * </p>
     *
     * @return A new {@code Task} instance with the marked status.
     */
    public abstract Task mark();

    /**
     * Unmarks a task.
     * <p>
     * Concrete subclasses must implement this method to define their own completion behavior.
     * </p>
     *
     * @return A new {@code Task} instance with the unmarked status.
     */
    public abstract Task unmark();

    /**
     * Converts the task into a string format suitable for file storage.
     * <p>
     * Concrete subclasses must define their own format for file representation.
     * </p>
     *
     * @return A formatted string representing the task.
     */
    public  abstract String toFileFormat();

    /**
     * Checks if the task is due on a specific date.
     * <p>
     * Concrete subclasses must implement this method to define due date logic.
     * </p>
     *
     * @param dueDate The date to check against.
     * @return {@code true} if the task is due on the given date, {@code false} otherwise.
     */
    public abstract boolean isDueOn(LocalDate dueDate);

    /**
     * Creates a {@code Task} object from a string representation stored in a file.
     * <p>
     * This method parses a line from a file and reconstructs the appropriate
     * {@code Task} object (either {@code ToDo}, {@code Deadline}, or {@code Event}).
     * </p>
     *
     * @param line The task string in the format "Type | ID | Status | Description | (Additional fields if applicable)".
     * @return A {@code Task} object corresponding to the parsed data, or {@code null} if the format is invalid.
     */
   public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        int id = Integer.parseInt(parts[1]);
        boolean isDone = parts[2].equals("1");
        String description = parts[3];

        switch (parts[0]) {
            case "T": return new ToDo(id, description, isDone);
            case "D": return new Deadline(id, description, isDone, parts[4]);
            case "E": return new Event(id, description, isDone, parts[4], parts[5]);
            default: return null;
        }
    }

    public String toString() {
        String icon = isDone ? "X" : " ";
        return "[ ] [" + icon +"] " + this.description;
    }
}