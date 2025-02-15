package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {}

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        return ui.printHelp();
    }
}