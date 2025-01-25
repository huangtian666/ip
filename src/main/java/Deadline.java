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
    Task toggleStatus(Task task) {
        boolean newStatus = !task.getStatus();
        if (newStatus){
            System.out.println("Good Job on completing your task! I've marked this task!");
        } else {
            System.out.println("I've unmarked your task!");
        }
        return new Deadline(task.getId(), task.getDescription(), newStatus, task.getEnd());

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
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return "[D] [" + icon +"] " + super.getDescription() + " (by: " + this.end + ")";
    }


}