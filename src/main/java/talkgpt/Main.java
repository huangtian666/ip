package talkgpt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;

import talkgpt.ui.DialogBox;


public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/shinchan.png"));
    private Image gptImage = new Image(this.getClass().getResourceAsStream("/images/doraemon.png"));

    @Override
    public void start(Stage stage) {
        //Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        //Handling user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Formatting the window to look as expected

        stage.setTitle("TalkGPT"); //set title of the window to talk GPT
        stage.setResizable(false); //prevents user from resizing the window
        stage.setMinHeight(600.0); //set min height
        stage.setMinWidth(400.0);  //set min width

        mainLayout.setPrefSize(400.0, 600.0); // set layout size (AnchorPane)

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // horizontal bar is never shown
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // vertical bar always shown
        scrollPane.setVvalue(1.0); // ensure scroll pane starts at the bottom
        scrollPane.setFitToWidth(true); //  the content inside the scroll pane will stretch to match the width.

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE); // window adjust base on size

        userInput.setPrefWidth(325.0); // the text input field is set to 325 pixels wide

        sendButton.setPrefWidth(55.0); // the send button is 55 pixels wide.

        AnchorPane.setTopAnchor(scrollPane, 1.0); // the scroll pane is anchored 1 pixel from the top

        AnchorPane.setBottomAnchor(sendButton, 5.0); // the send button is anchored 1 pixel from the bottom.
        AnchorPane.setRightAnchor(sendButton, 5.0);

        AnchorPane.setLeftAnchor(userInput , 5.0); // the text field is anchored 1 pixel from the left.
        AnchorPane.setBottomAnchor(userInput, 5.0);

        //More code to be added here later
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(new DialogBox(userInput.getText(), userImage));
        userInput.clear();
    }
}


