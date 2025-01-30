package talkgpt.command;

import talkgpt.ui.UI;
import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.task.*;

public class ToDoCommand extends Command {

    private String description;
    private static final int INDEX_OFFSET = 1;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(TaskList list, Storage storage, UI ui) {
        Task newTask = new ToDos(list.size() + INDEX_OFFSET, description);
        list.addTask(newTask, storage, ui);
        return false;
    }
}