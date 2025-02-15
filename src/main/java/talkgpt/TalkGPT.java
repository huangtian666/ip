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
    private final String filePath;
    private static final String FILE_PATH = "./data/tasks.txt";

    /**
     * Constructs a new TalkGPT instance.
     * <p>
     * It initializes the UI, loads tasks and filePath,
     * and handles any loading errors.
     * </p>
     */
    public TalkGPT() {
        this.ui = new Ui();
        this.filePath = FILE_PATH;
        this.storage = new Storage(FILE_PATH);
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
        String output = "";
        while (true) {
            String input = ui.getUserInput();
            Command c = Parser.parse(input, this.ui);
            output += c.execute(this.tasks, this.storage, this.ui);
        }
    }

    public String start() {
        return ui.start();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input, this.ui);
        String output = c.execute(this.tasks, this.storage, this.ui);
        return output;
    }

    /**
     * The main method that launches the TalkGPT program.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        new TalkGPT().run();
    }
}
