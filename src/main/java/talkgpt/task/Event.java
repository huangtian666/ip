package talkgpt.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDateTime start;
    private LocalDateTime end;

    public Event(int index, String description, String start, String end) {
        super(index, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.start = LocalDateTime.parse(end, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    public Event(int index, String description, boolean status, String start, String end) {
        super(index, description, status);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

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
        if (newStatus){
            System.out.println("Good Job on completing your task! I've marked this task!");
        } else {
            System.out.println("I've unmarked your task!");
        }
        return new Event(super.getId(), super.getDescription(), newStatus, this.getStart(), this.getEnd());
    }

    @Override
    public boolean isValid() {
        if (super.getDescription().isEmpty()) {
            System.out.println("Your description cannot be empty!");
            return false;
        } else if (this.getEnd().isBefore(this.getStart())) {
            System.out.println("Please enter a valid duration.");
            return false;
        } else {
            return true;
        }
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
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E] [" + icon +"] " + super.getDescription() + " (from: " + this.start.format(displayFormatter)
                + " to: " + this.end.format(displayFormatter) + ")";
    }
}