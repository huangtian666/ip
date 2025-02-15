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
    public String execute(TaskList list, Storage storage, Ui ui) {
        return list.findTask(this.search, ui);
    }
}
