package talkgpt.task;

import talkgpt.ui.Messages;
import talkgpt.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time.
 * <p>
 * This class extends {@code Task} and includes additional properties
 * to manage deadlines for tasks.
 * </p>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */
public class Deadline extends Task {

    private LocalDateTime end;

    /**
     * Constructs a {@code Deadline} task with the given index, description, and deadline.
     *
     * @param index       The task ID.
     * @param description The task description.
     * @param end         The deadline in the format "d/M/yyyy HHmm".
     */
    public Deadline(int index, String description, String end){
        super(index, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.end = LocalDateTime.parse(end, formatter);
    }

    /**
     * Constructs a {@code Deadline} task with a specified completion status.
     *
     * @param index       The task ID.
     * @param description The task description.
     * @param status      The completion status of the task.
     * @param end         The deadline in the format "d/M/yyyy HHmm".
     */
    public Deadline(int index, String description, boolean status, String end) {
        super(index, description, status);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.end = LocalDateTime.parse(end, formatter);
    }

    /**
     * Constructs a {@code Deadline} task with a pre-parsed {@code LocalDateTime} object.
     *
     * @param index       The task ID.
     * @param description The task description.
     * @param status      The completion status of the task.
     * @param end         The deadline as a {@code LocalDateTime} object.
     */
    public Deadline(int index, String description, boolean status, LocalDateTime end) {
        super(index, description, status);
        this.end = end;
    }

    @Override
    public LocalDateTime getStart() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public Task toggleStatus() {
        boolean newStatus = !this.getStatus();
        return new Deadline(super.getId(), super.getDescription(), newStatus, this.getEnd());
    }

    @Override
    public boolean isDueOn(LocalDate dueDate) {
        return this.end.toLocalDate().equals(dueDate);
    }

    @Override
    public String toFileFormat() { // D | id | isDone | description | deadline
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + super.getId() + " | " + (super.getStatus() ? "1" : "0") + " | " + super.getDescription()
                + " | " +  this.getEnd().format(fileFormatter);
    }

    /**
     * Returns a string representation of the task for display.
     * <p>
     * The format used is:
     * <pre>
     * [D] [X] description (by: MMM dd yyyy, h:mm a)
     * </pre>
     * </p>
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        String icon = super.getStatus() ? "X" : " ";
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D] [" + icon +"] " + super.getDescription() + " (by: " + this.end.format(displayFormatter) + ")";
    }
}