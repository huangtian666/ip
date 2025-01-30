import java.util.ArrayList;

abstract class Command {

    abstract boolean execute(TaskList list, Storage storage, UI ui);
}