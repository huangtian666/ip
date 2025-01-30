public class Messages {

    public enum Error {
        INVALID_DEADLINE("Invalid format! Use: deadline <description> /by <date>"),
        INVALID_EVENT("Invalid format! Use: event <description> /from <start> /to <end>"),
        EMPTY_TASK_LIST("You have no tasks yet!"),
        INVALID_TASK_INDEX("Invalid task index! Please enter a valid number."),
        DUPLICATE_TASK("Task already exists: "),
        INVALID_DATE_FORMAT("Invalid date format! Please use dd/MM/yyyy."),
        TASK_NOT_FOUND("No task found for the given date.");

        private final String message;

        Error(String message) {
            this.message = message;
        }

        public String get() {
            return "[ERROR] " + message;  // Add prefix for better readability
        }
    }

    public enum Info {
        TASK_DELETED("Your task has been deleted!"),
        TASK_CLEARED("All tasks have been cleared."),
        WELCOME_MESSAGE("Hello! I'm TalkGPT\nWhat can I do for you?"),
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

    public enum Warning {
        EMPTY_TASK_ID("Your Task ID cannot be empty!"),
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
