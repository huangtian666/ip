class DeleteCommand extends Command {

    private int id;

    DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    boolean execute(TaskList list, Storage storage, UI ui) {
        list.deleteTask(this.id, storage, ui);
        return false;
    }
}