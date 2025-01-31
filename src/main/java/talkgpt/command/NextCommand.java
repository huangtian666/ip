package talkgpt.command;//dummy command

import talkgpt.ui.Ui;
import talkgpt.TaskList;
import talkgpt.storage.Storage;

public class NextCommand extends Command {

    public NextCommand() {}

    @Override
    public boolean execute(TaskList list, Storage storage, Ui ui) {
        return false;
    }
}