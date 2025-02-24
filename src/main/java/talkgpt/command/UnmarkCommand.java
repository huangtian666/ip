package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class UnmarkCommand extends Command {

    private final int id;

    public UnmarkCommand(int id) {
        this.id = id;
    }

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        return list.handleUnMark(this.id, storage);
    }
}
