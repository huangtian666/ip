package talkgpt.command;

import talkgpt.ui.Messages;
import talkgpt.storage.Storage;
import talkgpt.TaskList;
import talkgpt.ui.Ui;

public class ClearCommand extends Command {

    public ClearCommand() {}

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        list.clear(storage);
        return Messages.Info.ZERO_TASK.get();
    }
}