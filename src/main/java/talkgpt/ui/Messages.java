package talkgpt.ui;

/**
 * Contains predefined messages categorized into errors, informational messages, and warnings.
 * <p>
 * This class provides structured messages for user interactions, ensuring
 * consistency and readability in the application's responses.
 * </p>
 *
 * <p>There are three categories of messages:</p>
 * <ul>
 *     <li>{@link Messages.Error} - Error messages indicating invalid input or failed operations.</li>
 *     <li>{@link Messages.Info} - Informational messages for user feedback.</li>
 *     <li>{@link Messages.Warning} - Warning messages for potential issues.</li>
 * </ul>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */
public class Messages {

    /**
     * Enum representing various error messages.
     * <p>
     * These messages indicate errors such as invalid input formats, missing tasks, or unknown commands.
     * Each message is prefixed with {@code [ERROR]} for clarity.
     * </p>
     */
    public enum Error {
        INVALID_DEADLINE("Invalid format! Use: deadline <description> /by <date>"),
        INVALID_EVENT("Invalid format! Use: event <description> /from <start> /to <end>"),
        EMPTY_TASK_LIST("You have no tasks yet!"),
        INVALID_TASK_INDEX("Invalid task index! Please enter a valid number."),
        DUPLICATE_TASK("talkgpt.task.Task already exists: "),
        INVALID_DATE_FORMAT("Invalid date format! Please use dd/MM/yyyy."),
        TASK_NOT_FOUND("No task found for the given date."),
        INVALID_INSTRUCTION("Sorry, I don't understand ;(.");
        private final String message;

        /**
         * Constructs an {@code Error} enum with the specified message.
         *
         * @param message The error message.
         */
        Error(String message) {
            this.message = message;
        }

        /**
         * Returns the formatted error message.
         *
         * @return A string containing the error message prefixed with "[ERROR]".
         */
        public String get() {
            return "[ERROR] " + message;  // Add prefix for better readability
        }
    }

    /**
     * Enum representing various informational messages.
     * <p>
     * These messages provide feedback to the user, such as confirmations for actions performed.
     * Each message is prefixed with {@code [INFO]} for clarity.
     * </p>
     */
    public enum Info {
        TASK_DELETED("Your task has been deleted!"),
        TASK_CLEARED("All tasks have been cleared."),
        WELCOME_MESSAGE("Hello! I'm talkgpt.TalkGPT\nWhat can I do for you?"),
        GOODBYE_MESSAGE("Goodbye! See you next time!"),
        TASK_ADDED("Your task has been added successfully!"),
        TASK_LIST("Your ToDo List is here!"),
        TASK_COUNT("You have %d tasks in your ToDo List now!"),
        NO_TASK_ON("No task due on this date."),
        ZERO_TASK("I have cleared your tasks! You have no task now"),
        TASK_DUE_ON("Tasks due on ");

        private final String message;

        Info(String message) {
            this.message = message;
        }

        public String get() {
            return "[INFO] " + message; // Prefix for better clarity
        }
    }

    /**
     * Enum representing various warning messages.
     * <p>
     * These messages indicate potential issues or missing input that require user attention.
     * Each message is prefixed with {@code [WARNING]} for clarity.
     * </p>
     */
    public enum Warning {
        EMPTY_TASK_ID("Your talkgpt.task.Task ID cannot be empty!"),
        EMPTY_DESCRIPTION("Please enter a task description!"),
        EMPTY_COMMAND("Your command cannot be empty!"),
        NO_TASKS_FOUND("No tasks found.");

        private final String message;

        Warning(String message) {
            this.message = message;
        }

        public String get() {
            return "[WARNING] " + message; // Prefix for warnings
        }
    }
}
