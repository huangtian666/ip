//dummy command

class NextCommand extends Command {

    NextCommand() {}

    @Override
    boolean execute(TaskList list, Storage storage, UI ui) {
        return false;
    }
}