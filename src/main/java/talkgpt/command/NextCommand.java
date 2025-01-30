package talkgpt.command;//dummy command

import talkgpt.ui.UI;
import talkgpt.TaskList;
import talkgpt.storage.Storage;

public class NextCommand extends Command {

    public NextCommand() {}

    @Override
    public boolean execute(TaskList list, Storage storage, UI ui) {
        return false;
    }
}