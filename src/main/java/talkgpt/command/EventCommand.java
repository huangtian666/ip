package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.task.*;
import talkgpt.ui.Ui;

public class EventCommand extends Command {

    private String description;
    private String start;
    private String end;
    private static final int INDEX_OFFSET = 1;

    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        Task newTask = new Event(list.size() + INDEX_OFFSET, this.description, this.start, this.end);
        return list.addTask(newTask, storage, ui);
    }

}