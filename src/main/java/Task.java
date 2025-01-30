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

    boolean getStatus() {
        return this.isDone;
    }

    void setId(int id) {
        this.id = id;
    }

    abstract String getStart();
    abstract String getEnd();
    abstract Task toggleStatus();
    abstract boolean isValid();
    abstract String toFileFormat();

    static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        int id = Integer.parseInt(parts[1]);
        boolean isDone = parts[2].equals("1");
        String description = parts[3];

        switch (parts[0]) {
            case "T": return new ToDos(id, description, isDone);
            case "D": return new Deadline(id, description, isDone, parts[4]);
            case "E": return new Event(id, description, isDone, parts[4], parts[5]);
            default: return null;
        }
    }

    public String toString() {
        String icon = isDone ? "X" : " ";
        return "[ ] [" + icon +"] " + this.description;
    }
}