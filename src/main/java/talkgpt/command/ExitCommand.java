package talkgpt.command;

import talkgpt.Main;
import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;
import talkgpt.ui.MainWindow;

import javafx.application.Platform;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        Platform.runLater(() -> {
            try {
                Thread.sleep(500); // Give time for UI to render message
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.exit();  // Close JavaFX application
            System.exit(0);   // Ensure full termination
        });
        
        return ui.end();
    }
}