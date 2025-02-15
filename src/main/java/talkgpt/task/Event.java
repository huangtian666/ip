package talkgpt.task;

import talkgpt.ui.Messages;
import talkgpt.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * <p>
 * This class extends {@code Task} and includes properties for start and end times.
 * </p>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */
public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an {@code Event} task with a specified index, description, and time range.
     *
     * @param index       The task ID.
     * @param description The event description.
     * @param start       The event start time in the format "d/M/yyyy HHmm".
     * @param end         The event end time in the format "d/M/yyyy HHmm".
     */
    public Event(int index, String description, String start, String end) {
        super(index, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    /**
     * Constructs an {@code Event} task with a specified completion status.
     *
     * @param index       The task ID.
     * @param description The event description.
     * @param status      The completion status of the task.
     * @param start       The event start time in the format "d/M/yyyy HHmm".
     * @param end         The event end time in the format "d/M/yyyy HHmm".
     */
    public Event(int index, String description, boolean status, String start, String end) {
        super(index, description, status);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    /**
     * Constructs an {@code Event} task with a pre-parsed {@code LocalDateTime} start and end time.
     *
     * @param index       The task ID.
     * @param description The event description.
     * @param status      The completion status of the task.
     * @param start       The event start time as a {@code LocalDateTime} object.
     * @param end         The event end time as a {@code LocalDateTime} object.
     */
    public Event(int index, String description, boolean status, LocalDateTime start, LocalDateTime end) {
        super(index, description, status);
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDateTime getStart() {
        return this.start;
    }
    @Override
    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public Task toggleStatus() {
        boolean newStatus = !super.getStatus();
        return new Event(super.getId(), super.getDescription(), newStatus, this.getStart(), this.getEnd());
    }

    @Override
    public boolean isDueOn(LocalDate dueDate) {
        return this.end.toLocalDate().equals(dueDate);
    }

    @Override
    public String toFileFormat() { // D | id | isDone | description | start | end
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + super.getId() + " | " + (super.getStatus() ? "1" : "0") + " | " + super.getDescription()
                + " | " +  this.getStart().format(fileFormatter) + " | " +  this.getEnd().format(fileFormatter);
    }

    @Override
    public String toString() {
        String icon = super.getStatus() ? "X" : " ";
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E] [" + icon +"] " + super.getDescription() + " (from: " + this.start.format(displayFormatter)
                + " to: " + this.end.format(displayFormatter) + ")";
    }
}