package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class HelloCommand extends Command {

    public HelloCommand() {}

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        return "Helloooooo! Nice to see you there!";
    }
}
