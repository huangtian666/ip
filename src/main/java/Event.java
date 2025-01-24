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

    Task toggleStatus(Task task) {
        boolean newStatus = !task.getStatus();
        if (newStatus){
            System.out.println("Good Job on completing your task! I've marked this task!");
        } else {
            System.out.println("I've unmarked your task!");
        }
        return new Event(task.getId(), task.getDescription(), newStatus, task.getStart(), task.getEnd());

    }

    @Override
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return "[E] [" + icon +"] " + super.getDescription()
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}