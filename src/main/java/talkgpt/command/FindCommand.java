package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class FindCommand extends Command {

    private String search;

    public FindCommand(String searchString) {
        this.search = searchString;
    }

    @Override
    public boolean execute(TaskList list, Storage storage, Ui ui) {
        list.findTask(this.search, ui);
        return false;
    }
}
