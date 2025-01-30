package talkgpt.command;

import talkgpt.ui.UI;
import talkgpt.TaskList;
import talkgpt.storage.Storage;

public class MarkCommand extends Command {

    private int id;

    public MarkCommand(int id) {
        this.id = id;
    }

    @Override
    public boolean execute(TaskList list, Storage storage, UI ui) {
        list.handleMark(this.id, storage, ui);
        return false;
    }
}