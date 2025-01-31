package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {}

    @Override
    public boolean execute(TaskList list, Storage storage, Ui ui) {
        ui.printHelp();
        return false;
    }
}