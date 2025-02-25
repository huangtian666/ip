package talkgpt.ui;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * 🌟 Contains predefined messages categorized into errors, informational messages, and warnings.
 *
 * <p>Ensures structured, readable, and engaging responses for user interactions.</p>
 *
 * 🔹 **Message Categories:**
 * - 🛑 {@link Messages.Error} - Error messages for invalid input or failures.
 * - ℹ️ {@link Messages.Info} - Informational messages for user feedback.
 * - ⚠️ {@link Messages.Warning} - Warnings for potential issues.
 *
 * @author Huang Tian
 * @version 1.2
 * @since 2025-02-01
 */
public class Messages {

    /**
     * 🚨 Error messages for invalid inputs or failed operations.
     */
    public enum Error {
        INVALID_DEADLINE("Invalid format! Use: deadline <description> /by dd/mm/yyyy hhmm"),
        INVALID_EVENT("Invalid format! Use: event <description> /from <start> /to <end>"),
        EMPTY_TASK_LIST( "You have no tasks yet!"),
        INVALID_TASK_INDEX("Invalid task index! Please enter a valid number."),
        DUPLICATE_TASK("This task already exists: "),
        INVALID_DATE_FORMAT("Invalid date format! Please use dd/MM/yyyy."),
        TASK_NOT_FOUND("No task found for the given date."),
        NO_TASK_FOUND("No task found matching your search."),
        INVALID_TASK("Invalid task! Please enter a valid command."),
        MARKED_TASK("The task is already marked as done!"),
        UNMARKED_TASK("The task is already unmarked!"),
        WRONG_DEADLINE("The deadline entered needs to be after the current time: " + LocalDateTime.now()),
        WRONG_START_DATE("The start date entered needs to be before the end date.");

        private final String message;

        Error(String message) {
            this.message = message;
        }

        public String get() {
            return "[ERROR] " + message;
        }

        /** 🌀 Returns a dynamic random error message each time it's called */
        public static String getInvalidInstructionMessage() {
            String[] randomMessages = {
                    "Oops! I didn't get that. Can you try again?",
                    "Sorry, I don't understand. Could you rephrase that?",
                    "Hmm... that command confuses me!",
                    "I'm not sure what you mean! Can you be more specific?",
                    "Oops! That doesn't seem like a valid command."
            };
            return "[ERROR] " + randomMessages[new Random().nextInt(randomMessages.length)];
        }
    }

    /**
     * ℹ️ Informational messages to confirm actions and provide guidance.
     */
    public enum Info {
        TASK_DELETED("Your task has been deleted successfully!"),
        TASK_CLEARED(" All tasks have been cleared."),
        TASK_ADDED("Your task has been added successfully!"),
        TASK_LIST("Here is your To-Do list:"),
        TASK_COUNT("You have %d tasks in your To-Do list."),
        NO_TASK_ON("No tasks due on this date."),
        ZERO_TASK("All tasks have been cleared! You have no tasks now."),
        TASK_DUE_ON("Tasks due on "),
        COMPLETE_TASK(" Well done! You've completed a task!"),
        UNMARK_TASK("You have unmarked this task!");

        private final String message;

        Info(String message) {
            this.message = message;
        }

        public String get() {
            return "[INFO] " + message;
        }
    }

    /**
     * ⚠️ Warning messages to alert users about potential issues.
     */
    public enum Warning {
        EMPTY_TASK_ID("Your task ID cannot be empty!"),
        EMPTY_DESCRIPTION("Please enter a task description!"),
        EMPTY_COMMAND("Your command cannot be empty!"),
        NO_TASKS_FOUND(" No tasks found!");

        private final String message;

        Warning(String message) {
            this.message = message;
        }

        public String get() {
            return "[WARNING] " + message;
        }
    }
}
