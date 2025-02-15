package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.task.*;
import talkgpt.ui.Ui;

public class DeadlineCommand extends Command {

    private String description;
    private String deadline;
    private static final int INDEX_OFFSET = 1;

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        Task newTask = new Deadline(list.size() + INDEX_OFFSET, description, deadline);
        return list.addTask(newTask, storage, ui);
    }
}