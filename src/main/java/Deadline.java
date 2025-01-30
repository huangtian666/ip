class Deadline extends Task{

    private String end;

    Deadline(int index, String description, String end){
        super(index, description);
        this.end = end;
    }

    Deadline(int index, String description, boolean status, String end) {
        super(index, description, status);
        this.end = end;
    }

    String getStart() { return "";}

    String getEnd(){
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
        } else if (this.getEnd().isEmpty()) {
            System.out.println("Please enter a valid deadline.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    String toFileFormat() { // D | id | isDone | description | deadline
        return "D | " + super.getId() + " | " + (super.getStatus() ? "1" : "0") + " | " + super.getDescription()
                + " | " +  this.getEnd();
    }

    @Override
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return "[D] [" + icon +"] " + super.getDescription() + " (by: " + this.end + ")";
    }


}