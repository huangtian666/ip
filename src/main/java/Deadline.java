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

   String getEnd(){
        return this.end;
    }

    @Override
    public String toString(){
        String icon = super.getStatus() ? "X" : " ";
        return super.getId() + ". [D] [" + icon +"] " + super.getDescription() + " (by: " + this.end + ")";
    }


}