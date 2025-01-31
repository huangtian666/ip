package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class DeleteCommand extends Command {

    private int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public boolean execute(TaskList list, Storage storage, Ui ui) {
        list.deleteTask(this.id, storage, ui);
        return false;
    }
}