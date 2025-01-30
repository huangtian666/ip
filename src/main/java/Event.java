class Event extends Task{

    private String start;
    private String end;

    Event(int index, String description, String start, String end) {
        super(index, description);
        this.start = start;
        this.end = end;
    }

    Event(int index, String description, boolean status, String start, String end) {
        super(index, description, status);
        this.start = start;
        this.end = end;
    }

    @Override
    String getStart() {
        return this.start;
    }
    @Override
    String getEnd() {
        return this.end;
    }

    @Override
    Task toggleStatus() {
        boolean newStatus = !super.getStatus();
        if (newStatus){
            System.out.println("Good Job on completing your task! I've marked this task!");
        } else {
            System.out.println("I've unmarked your task!");
        }
        return new Event(super.getId(), super.getDescription(), newStatus, this.getStart(), this.getEnd());
    }

    @Override
    boolean isValid() {
        if (super.getDescription().isEmpty()) {
            System.out.println("Your description cannot be empty!");
            return false;
        } else if (this.getEnd().isEmpty() || this.getStart().isEmpty()) {
            System.out.println("Please enter a valid duration.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    String toFileFormat() { // D | id | isDone | description | start | end
        return "E | " + super.getId() + " | " + (super.getStatus() ? "1" : "0") + " | " + super.getDescription()
                + " | " +  this.getStart() + " | " +  this.getEnd();
    }

    @Override
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return "[E] [" + icon +"] " + super.getDescription()
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}