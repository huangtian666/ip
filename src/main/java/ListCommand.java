class ListCommand extends Command {

    ListCommand() {}

    @Override
    boolean execute(TaskList list, Storage storage, UI ui) {
        list.listTasks(ui);
        return false;
    }
}