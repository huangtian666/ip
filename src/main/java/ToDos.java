class ToDos {

    private String description;
    private int id;

    ToDos(int index, String description) {
        this.id = index;
        this.description = description;
    }

    int getId() {
        return this.id;
    }

    String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.id + ". " + this.description;
    }

}