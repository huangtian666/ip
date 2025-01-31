package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public boolean execute(TaskList list, Storage storage, Ui ui) {
        ui.end();
        return true;
    }
}