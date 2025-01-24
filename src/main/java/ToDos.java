class ToDos extends Task{


    ToDos(int index, String description) {
        super(index, description);
    }

    ToDos(int index, String description, boolean status) {
        super(index, description, status);
    }

    String getStart() { return "";}

    String getEnd() { return "";}

    Task toggleStatus(Task task) {
        boolean newStatus = !task.getStatus();
        if (newStatus){
            System.out.println("Good Job on completing your task! I've marked this task!");
        } else {
            System.out.println("I've unmarked your task!");
        }
        return new ToDos(task.getId(), task.getDescription(), newStatus);

    }

    @Override
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return super.getId() + ". [T] [" + icon +"] " + super.getDescription();
    }
}