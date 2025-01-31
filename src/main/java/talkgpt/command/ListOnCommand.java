package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class ListOnCommand extends Command {

    private String dueDate;

    public ListOnCommand(String date) {
        this.dueDate = date;
    }

    @Override
    public boolean execute(TaskList list, Storage storage, Ui ui) {
        list.listTaskDueOn(this.dueDate, ui);
        return false;
    }
}