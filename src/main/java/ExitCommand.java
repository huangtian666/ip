class ExitCommand extends Command {

    ExitCommand() {}

    @Override
    boolean execute(TaskList list, Storage storage, UI ui) {
        ui.end();
        return true;
    }
}