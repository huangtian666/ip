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
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return "[D] [" + icon +"] " + super.getDescription() + " (by: " + this.end + ")";
    }


}