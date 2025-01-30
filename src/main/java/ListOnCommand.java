class ListOnCommand extends Command {

    private String dueDate;

    ListOnCommand(String date) {
        this.dueDate = date;
    }

    @Override
    boolean execute(TaskList list, Storage storage, UI ui) {
        list.listTaskDueOn(this.dueDate, ui);
        return false;
    }
}