package talkgpt.command;

import talkgpt.ui.UI;
import talkgpt.TaskList;
import talkgpt.storage.Storage;

public class HelpCommand extends Command {

    public HelpCommand() {}

    @Override
    public boolean execute(TaskList list, Storage storage, UI ui) {
        ui.printHelp();
        return false;
    }
}