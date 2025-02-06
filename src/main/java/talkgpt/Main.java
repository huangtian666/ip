package talkgpt;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import talkgpt.ui.DialogBox;




/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private TalkGPT talkgpt = new TalkGPT();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("TalkGPT");
            stage.setScene(scene);
            stage.setMinHeight(250);
            stage.setMinWidth(420);
            fxmlLoader.<MainWindow>getController().setTalkGpt(talkgpt);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
