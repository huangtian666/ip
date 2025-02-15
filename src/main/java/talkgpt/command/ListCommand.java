package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        return list.listTasks();
    }
}