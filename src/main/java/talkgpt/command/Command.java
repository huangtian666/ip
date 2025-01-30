package talkgpt.command;

import talkgpt.ui.UI;
import talkgpt.TaskList;
import talkgpt.storage.Storage;

public abstract class Command {

    abstract public boolean execute(TaskList list, Storage storage, UI ui);
}