package talkgpt.command;

import talkgpt.ui.UI;
import talkgpt.TaskList;
import talkgpt.storage.Storage;

public class DeleteCommand extends Command {

    private int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public boolean execute(TaskList list, Storage storage, UI ui) {
        list.deleteTask(this.id, storage, ui);
        return false;
    }
}