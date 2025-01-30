import java.time.LocalDate;
import java.time.LocalDateTime;

class ToDos extends Task{

    ToDos(int index, String description) {
        super(index, description);
    }

    ToDos(int index, String description, boolean status) {
        super(index, description, status);
    }

    @Override
    LocalDateTime getStart() {
        return LocalDateTime.now();
    }

    @Override
    LocalDateTime getEnd() {
        return LocalDateTime.now();
    }

    @Override
    Task toggleStatus() {
        boolean newStatus = !super.getStatus();
        if (newStatus){
            System.out.println("Good Job on completing your task! I've marked this task!");
        } else {
            System.out.println("I've unmarked your task!");
        }
        return new ToDos(super.getId(), super.getDescription(), newStatus);

    }

    @Override
    boolean isValid() {
        if (super.getDescription().isEmpty()) {
            System.out.println("Please enter a description.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    boolean isDueOn(LocalDate dueDate) {
        return false;
    }

    @Override
    String toFileFormat() { // D | id | isDone | description
        return "T | " + super.getId() + " | " + (super.getStatus() ? "1" : "0") + " | " + super.getDescription();
    }

    @Override
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return "[T] [" + icon +"] " + super.getDescription();
    }
}