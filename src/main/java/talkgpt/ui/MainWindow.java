package talkgpt.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import talkgpt.TalkGPT;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TalkGPT talkgpt;
    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/shinchan.png"));
    private Image gptImage = new Image(this.getClass().getResourceAsStream("/images/doraemon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setPromptText("Type here...");
    }

    /** Injects the talkgpt instance */
    public void setTalkGpt(TalkGPT d) {
        talkgpt = d;
        dialogContainer.getChildren().add(
                DialogBox.getGPTDialog(talkgpt.start(), gptImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing TalkGPT's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            end(input);
            return;
        }
        String response = talkgpt.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGPTDialog(response, gptImage)
        );
        userInput.clear();
    }

    public void end(String input) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();
        dialogContainer.getChildren().add(
                DialogBox.getGPTDialog(talkgpt.end(), gptImage)
        );

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            Platform.exit(); // Close JavaFX application
            System.exit(0);
        });
        delay.play();
    }
}
