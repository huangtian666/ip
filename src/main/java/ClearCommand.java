class ClearCommand extends Command {

    ClearCommand() {}

    @Override
    boolean execute(TaskList list, Storage storage, UI ui) {
        list.clear(storage);
        ui.showMessage(Messages.Info.ZERO_TASK.get());
        return false;
    }
}