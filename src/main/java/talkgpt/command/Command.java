package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public abstract class Command {

    abstract public boolean execute(TaskList list, Storage storage, Ui ui);
}