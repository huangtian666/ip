import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task{

    private LocalDateTime end;

    Deadline(int index, String description, String end){
        super(index, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.end = LocalDateTime.parse(end, formatter);
    }

    Deadline(int index, String description, boolean status, String end) {
        super(index, description, status);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.end = LocalDateTime.parse(end, formatter);
    }

    Deadline(int index, String description, boolean status, LocalDateTime end) {
        super(index, description, status);
        this.end = end;
    }

    @Override
    LocalDateTime getStart() {
        return LocalDateTime.now();
    }

    @Override
    LocalDateTime getEnd(){
        return this.end;
    }
    @Override
    Task toggleStatus() {
        boolean newStatus = !this.getStatus();
        if (newStatus){
            System.out.println("Good Job on completing your task! I've marked this task!");
        } else {
            System.out.println("I've unmarked your task!");
        }
        return new Deadline(super.getId(), super.getDescription(), newStatus, this.getEnd());
    }

    @Override
    boolean isValid() {
        if (super.getDescription().isEmpty()) {
            System.out.println("Your description cannot be empty!");
            return false;
        } else if (this.getEnd().isBefore(LocalDateTime.now())) {
            System.out.println("Please enter a valid deadline.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    boolean isDueOn(LocalDate dueDate) {
        return this.end.toLocalDate().equals(dueDate);
    }

    @Override
    String toFileFormat() { // D | id | isDone | description | deadline
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + super.getId() + " | " + (super.getStatus() ? "1" : "0") + " | " + super.getDescription()
                + " | " +  this.getEnd().format(fileFormatter);
    }

    @Override
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D] [" + icon +"] " + super.getDescription() + " (by: " + this.end.format(displayFormatter) + ")";
    }


}