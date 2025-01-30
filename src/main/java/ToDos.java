class ToDos extends Task{

    ToDos(int index, String description) {
        super(index, description);
    }

    ToDos(int index, String description, boolean status) {
        super(index, description, status);
    }

    String getStart() { return "";}

    String getEnd() { return "";}

    @Override
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
    boolean isValid() {
        if (super.getDescription().isEmpty()) {
            System.out.println("Please enter a description.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return "[T] [" + icon +"] " + super.getDescription();
    }
}