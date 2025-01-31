package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public boolean execute(TaskList list, Storage storage, Ui ui) {
        list.listTasks(ui);
        return false;
    }
}