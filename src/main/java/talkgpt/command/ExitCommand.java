package talkgpt.command;

import talkgpt.ui.UI;
import talkgpt.TaskList;
import talkgpt.storage.Storage;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public boolean execute(TaskList list, Storage storage, UI ui) {
        ui.end();
        return true;
    }
}