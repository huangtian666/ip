package talkgpt.ui;

import java.util.Scanner;

/**
 * Handles user interaction for the TalkGPT application.
 * <p>
 * This class is responsible for displaying messages, reading user input, and
 * formatting responses. It provides methods to show system messages, print
 * available commands, and manage user interactions.
 * </p>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */
public class Ui {

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String start() {
        return "Hello! I'm TalkGPT.\nWhat can I do for you?";
    }

    public String end() {
        return "Goodbye! See you next time!";
    }

    public String getUserInput() {
        return sc.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting fresh...");
    }

    /**
     * Displays a formatted message using the specified {@code Messages.Info} enum.
     * <p>
     * This method allows formatted output with variable arguments.
     * </p>
     *
     * @param message The {@code Messages.Info} enum containing the message format.
     * @param args    The arguments to be formatted into the message.
     */
    public String showFormattedMessage(Messages.Info message, Object... args) {
        return String.format(message.get() + "%n", args);
    }



    public String printHelp() {
        return("Available commands:\n" +
                        "1. list - Display all tasks\n" +
                        "2. todo <description> - Add a ToDo\n" +
                        "3. deadline <description> /by <date: dd/mm/yyyy time> - Add a Deadline\n" +
                        "4. event <description> /from <start: dd/mm/yyyy time> /to <end: dd/mm/yyyy time> - Add an Event\n" +
                        "5. mark <taskId> - Mark a task as completed\n" +
                        "6. unmark <taskId> - Unmark a task\n" +
                        "7. delete <taskId> - Delete a task\n" +
                        "8. bye - Exit the application\n" +
                        "9. clear - Clear all tasks\n" +
                        "10. list on <date> - List tasks due on this date\n" +
                        "11. help - Print all available commands"
        );

    }
}