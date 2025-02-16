package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        return ui.end();
    }
}