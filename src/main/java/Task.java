abstract class Task {

    private String description;
    private int id;
    private boolean isDone;

    Task(int index, String description) {
        this.id = index;
        this.description = description;
        this.isDone = false;
    }

    Task(int index, String description, boolean status) {
        this.id = index;
        this.description = description;
        this.isDone = status;
    }

    int getId() {
        return this.id;
    }

    String getDescription() {
        return this.description;
    }

    abstract String getStart();
    abstract String getEnd();
    abstract Task toggleStatus(Task task);

    boolean getStatus() {
        return this.isDone;
    }

    public String toString() {
        String icon = isDone ? "X" : " ";
        return "[ ] [" + icon +"] " + this.description;
    }
}