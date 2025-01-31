package talkgpt;

import talkgpt.command.Command;
import talkgpt.parser.Parser;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

/**
 * The entry point of the TalkGPT program.
 * <p>
 * This class initializes the necessary components, including the user interface,
 * task storage, and task list. It continuously processes user input until the
 * program exits.
 * </p>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */

public class TalkGPT {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new TalkGPT instance.
     * <p>
     * It initializes the UI, loads tasks from the specified file path,
     * and handles any loading errors.
     * </p>
     *
     * @param filePath The file path where tasks are stored.
     */
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

    /**
     * Starts the TalkGPT program.
     * <p>
     * This method runs the main loop, continuously processing user input
     * until the exit command is issued.
     * </p>
     */
    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.getUserInput();
            Command c = Parser.parse(input, this.ui);
            isExit = c.execute(this.tasks, this.storage, this.ui);
        }
    }

    /**
     * The main method that launches the TalkGPT program.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        new TalkGPT("./data/tasks.txt").run();
    }
}
