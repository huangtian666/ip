package talkgpt;

import talkgpt.command.Command;
import talkgpt.parser.Parser;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

public class TalkGPT {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public TalkGPT(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError();
            loadedTasks = new TaskList();
        }
        this.tasks = loadedTasks;
    }

    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.getUserInput();
            Command c = Parser.parse(input, this.ui);
            isExit = c.execute(this.tasks, this.storage, this.ui);
        }
    }

    public static void main(String[] args) {
        new TalkGPT("./data/tasks.txt").run();
    }
}
