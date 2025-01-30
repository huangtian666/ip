package talkgpt.command;

import talkgpt.ui.Messages;
import talkgpt.ui.UI;
import talkgpt.storage.Storage;
import talkgpt.TaskList;

public class ClearCommand extends Command {

    public ClearCommand() {}

    @Override
    public boolean execute(TaskList list, Storage storage, UI ui) {
        list.clear(storage);
        ui.showMessage(Messages.Info.ZERO_TASK.get());
        return false;
    }
}