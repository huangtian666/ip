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
    public String execute(TaskList list, Storage storage, Ui ui) {
        return list.handleMark(this.id, storage);
    }
}