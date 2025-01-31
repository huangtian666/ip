package talkgpt.command;

import talkgpt.ui.Messages;
import talkgpt.storage.Storage;
import talkgpt.TaskList;
import talkgpt.ui.Ui;

public class ClearCommand extends Command {

    public ClearCommand() {}

    @Override
    public boolean execute(TaskList list, Storage storage, Ui ui) {
        list.clear(storage);
        ui.showMessage(Messages.Info.ZERO_TASK.get());
        return false;
    }
}