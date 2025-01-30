package talkgpt.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task {

    private String description;
    private int id;
    private boolean isDone;

    public Task(int index, String description) {
        this.id = index;
        this.description = description;
        this.isDone = false;
    }

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
    public abstract Task toggleStatus();
    public abstract boolean isValid();
    public  abstract String toFileFormat();
    public abstract boolean isDueOn(LocalDate dueDate);

   public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        int id = Integer.parseInt(parts[1]);
        boolean isDone = parts[2].equals("1");
        String description = parts[3];

        switch (parts[0]) {
            case "T": return new ToDos(id, description, isDone);
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