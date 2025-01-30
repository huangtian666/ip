package talkgpt.command;

import talkgpt.ui.UI;
import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.task.*;

public class DeadlineCommand extends Command {

    private String description;
    private String deadline;
    private static final int INDEX_OFFSET = 1;

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public boolean execute(TaskList list, Storage storage, UI ui) {
        Task newTask = new Deadline(list.size() + INDEX_OFFSET, description, deadline);
        list.addTask(newTask, storage, ui);
        return false;
    }
}