class ToDos {

    private String description;
    private int id;
    private Status status;

    ToDos(int index, String description) {
        this.id = index;
        this.description = description;
        this.status = Status.NOT_DONE;
    }

    ToDos(int index, String description, Status status) {
        this.id = index;
        this.description = description;
        this.status = status;
    }

    int getId() {
        return this.id;
    }

    String getDescription() {
        return this.description;
    }

    Status getStatus() {
        return this.status;
    }

    public String toString() {
        String icon = "";
        if (this.status == Status.DONE) {
            icon = "[X] ";
        } else {
            icon = "[ ] ";
        }
        return this.id + ". " + icon + this.description;
    }
}