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

    String getStart() {
        return this.start;
    }

    String getEnd() {
        return this.end;
    }

    @Override
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return super.getId() + ". [E] [" + icon +"] " + super.getDescription()
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}