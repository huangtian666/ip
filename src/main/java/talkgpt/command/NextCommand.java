package talkgpt.command;//dummy command

import talkgpt.ui.Ui;
import talkgpt.TaskList;
import talkgpt.storage.Storage;

public class NextCommand extends Command {

    private final String output;

    public NextCommand(String output) {
        this.output = output;

    }

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        return output;
    }
}