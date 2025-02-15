package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.task.*;
import talkgpt.ui.Ui;

public class ToDoCommand extends Command {

    private String description;
    private static final int INDEX_OFFSET = 1;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        Task newTask = new ToDo(list.size() + INDEX_OFFSET, description);
        return list.addTask(newTask, storage, ui);
    }
}