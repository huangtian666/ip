class MarkCommand extends Command {

    private int id;

    MarkCommand(int id) {
        this.id = id;
    }

    @Override
    boolean execute(TaskList list, Storage storage, UI ui) {
        list.handleMark(this.id, storage, ui);
        return false;
    }
}