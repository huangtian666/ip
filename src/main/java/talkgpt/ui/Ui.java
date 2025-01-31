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

    public void start() {
        System.out.println("Hello! I'm TalkGPT.\nWhat can I do for you?");
    }

    public void end() {
        System.out.println("Goodbye! See you next time!");
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
    public void showFormattedMessage(Messages.Info message, Object... args) {
        System.out.printf(message.get() + "%n", args);
    }

    public void emptyLine() {
        System.out.println();
    }

    public void printHelp() {
        System.out.println("Available commands:");
        System.out.println("1. list - Display all tasks");
        System.out.println("2. todo <description> - Add a ToDo");
        System.out.println("3. deadline <description> /by <date: dd/mm/yyyy time> - Add a talkgpt.task.Deadline");
        System.out.println("4. event <description> " +
                "/from <start: dd/mm/yyyy time> /to <end: dd/mm/yyyy time> - Add an talkgpt.task.Event");
        System.out.println("5. mark <taskId> - Mark a task as completed");
        System.out.println("6. unmark <taskId> - Unmark a task");
        System.out.println("7. delete <taskId> - Delete a task");
        System.out.println("8. bye - Exit the application");
        System.out.println("9. clear - Clear all tasks");
        System.out.println("10. list on <date> - List tasks due on this date");
        System.out.println("11. help - Print all available commands");
    }
}