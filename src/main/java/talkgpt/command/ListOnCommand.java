package talkgpt.command;

import talkgpt.ui.UI;
import talkgpt.TaskList;
import talkgpt.storage.Storage;

public class ListOnCommand extends Command {

    private String dueDate;

    public ListOnCommand(String date) {
        this.dueDate = date;
    }

    @Override
    public boolean execute(TaskList list, Storage storage, UI ui) {
        list.listTaskDueOn(this.dueDate, ui);
        return false;
    }
}