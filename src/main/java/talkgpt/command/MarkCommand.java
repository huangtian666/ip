package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class MarkCommand extends Command {

    private int id;

    public MarkCommand(int id) {
        this.id = id;
    }

    @Override
    public boolean execute(TaskList list, Storage storage, Ui ui) {
        list.handleMark(this.id, storage, ui);
        return false;
    }
}