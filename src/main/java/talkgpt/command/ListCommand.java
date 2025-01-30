package talkgpt.command;

import talkgpt.ui.UI;
import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.task.*;

public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public boolean execute(TaskList list, Storage storage, UI ui) {
        list.listTasks(ui);
        return false;
    }
}