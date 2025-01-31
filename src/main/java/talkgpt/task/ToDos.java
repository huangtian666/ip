package talkgpt.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a simple to-do task.
 * <p>
 * This class extends {@code Task} and represents tasks that do not have a specific
 * deadline or event duration. To-do tasks can be marked as complete or incomplete.
 * </p>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */
public class ToDos extends Task{

    /**
     * Constructs a {@code ToDos} task with the given ID and description.
     * The task is initially marked as not completed.
     *
     * @param index       The unique ID of the task.
     * @param description The task description.
     */
    public ToDos(int index, String description) {
        super(index, description);
    }

    /**
     * Constructs a {@code ToDos} task with the given ID, description, and completion status.
     *
     * @param index       The unique ID of the task.
     * @param description The task description.
     * @param status      The completion status of the task.
     */
    public ToDos(int index, String description, boolean status) {
        super(index, description, status);
    }

    @Override
    public LocalDateTime getStart() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime getEnd() {
        return LocalDateTime.now();
    }

    @Override
    public Task toggleStatus() {
        boolean newStatus = !super.getStatus();
        if (newStatus){
            System.out.println("Good Job on completing your task! I've marked this task!");
        } else {
            System.out.println("I've unmarked your task!");
        }
        return new ToDos(super.getId(), super.getDescription(), newStatus);

    }

    @Override
    public boolean isValid() {
        if (super.getDescription().isEmpty()) {
            System.out.println("Please enter a description.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isDueOn(LocalDate dueDate) {
        return false;
    }

    @Override
    public String toFileFormat() { // D | id | isDone | description
        return "T | " + super.getId() + " | " + (super.getStatus() ? "1" : "0") + " | " + super.getDescription();
    }

    @Override
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return "[T] [" + icon +"] " + super.getDescription();
    }
}