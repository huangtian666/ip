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
    public String execute(TaskList list, Storage storage, Ui ui) {
        return list.listTaskDueOn(this.dueDate);
    }
}