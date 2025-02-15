package talkgpt.parser;

import talkgpt.command.*;
import talkgpt.ui.Messages;
import talkgpt.ui.Ui;

/**
 * Parses user input and returns the corresponding command.
 * <p>
 * This class processes raw user input, determines the appropriate command,
 * and creates an instance of the respective command class. It handles
 * various commands such as adding, deleting, marking tasks, and listing them.
 * </p>
 *
 * <p>Supported commands:</p>
 * <ul>
 *     <li>{@code list} - Displays all tasks.</li>
 *     <li>{@code bye} - Exits the application.</li>
 *     <li>{@code mark <taskId>} - Marks a task as completed.</li>
 *     <li>{@code unmark <taskId>} - Unmarks a task.</li>
 *     <li>{@code delete <taskId>} - Deletes a task.</li>
 *     <li>{@code clear} - Clears all tasks.</li>
 *     <li>{@code todo <description>} - Adds a To-Do task.</li>
 *     <li>{@code deadline <description> /by <date>} - Adds a Deadline task.</li>
 *     <li>{@code event <description> /from <start> /to <end>} - Adds an Event task.</li>
 *     <li>{@code list on <date>} - Lists tasks due on a specific date.</li>
 *     <li>{@code help} - Displays available commands.</li>
 * </ul>
 *
 * <p>If an invalid command is entered, the parser returns a {@code NextCommand},
 * allowing the user to try again.</p>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */
public class Parser {
    private static final int MARK_COMMAND_LENGTH = 2;
    private static final int DELETE_COMMAND_LENGTH = 2;
    private static final int TODO_COMMAND_LENGTH = 5;
    private static final int DEADLINE_COMMAND_LENGTH = 9;
    private static final int EVENT_COMMAND_LENGTH = 6;
    private static final int FIND_COMMAND_LENGTH = 2;
    private static final int DATE_STARTING_INDEX = 8;
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";

    /**
     * Parses the user input and returns the corresponding command.
     * <p>
     * This method analyzes the input string and determines the appropriate
     * command based on predefined patterns. It validates required parameters
     * before constructing command objects.
     * </p>
     *
     * @param request The user input string.
     * @param ui      The user interface to display messages in case of errors.
     * @return The corresponding {@code Command} object.
     */
    public static Command parse(String request, Ui ui) {
        request = request.trim();

        if (request.isEmpty()) {
            return new NextCommand(Messages.Warning.EMPTY_COMMAND.get());
        }

        String[] requestArray = request.split(" ");
        String command = requestArray[0]; // Extract the first word as the command

        switch (command) {
        case "list":
            return new ListCommand();

        case "bye":
            return new ExitCommand();

        case "help":
            return new HelpCommand();

        case "clear":
            return new ClearCommand();

        case "hi":
        case "hello":
            return new HelloCommand();

        case "mark":
        case "unmark":
            if (requestArray.length < MARK_COMMAND_LENGTH) {
                return new NextCommand(Messages.Warning.EMPTY_TASK_ID.get());
            }
            int taskId = Integer.parseInt(requestArray[1]);
            return new MarkCommand(taskId);

        case "delete":
            if (requestArray.length < DELETE_COMMAND_LENGTH) {
                return new NextCommand(Messages.Warning.EMPTY_TASK_ID.get());
            }
            int deleteTaskId = Integer.parseInt(requestArray[1]);
            return new DeleteCommand(deleteTaskId);

        case "list_on":
            String dateString = request.substring(DATE_STARTING_INDEX).trim(); // Extract the date string
            return new ListOnCommand(dateString);

        case "todo":
            if (request.length() <= TODO_COMMAND_LENGTH) {
                return new NextCommand(Messages.Warning.EMPTY_DESCRIPTION.get());
            }
            String todoDescription = request.substring(TODO_COMMAND_LENGTH).trim();
            return new ToDoCommand(todoDescription);

        case "deadline":
            if (!request.contains(DEADLINE_SEPARATOR)) {
                return new NextCommand(Messages.Error.INVALID_DEADLINE.get());
            }
            String[] deadlineParts = request.split(DEADLINE_SEPARATOR);
            if (deadlineParts.length < 2) {
                return new NextCommand(Messages.Error.INVALID_DEADLINE.get());
            }
            return new DeadlineCommand(deadlineParts[0].substring(DEADLINE_COMMAND_LENGTH).trim(), deadlineParts[1].trim());

        case "event":
            if (!request.contains(EVENT_FROM_SEPARATOR) || !request.contains(EVENT_TO_SEPARATOR)) {
                return new NextCommand(Messages.Error.INVALID_EVENT.get());
            }
            String[] eventParts = request.split(EVENT_FROM_SEPARATOR);
            if (eventParts.length < 2 || !eventParts[1].contains(EVENT_TO_SEPARATOR)) {
                return new NextCommand(Messages.Error.INVALID_EVENT.get());
            }
            String eventDescription = eventParts[0].substring(EVENT_COMMAND_LENGTH).trim();
            String[] eventDuration = eventParts[1].split(EVENT_TO_SEPARATOR);
            return new EventCommand(eventDescription, eventDuration[0].trim(), eventDuration[1].trim());

        case "find":
            if (requestArray.length < FIND_COMMAND_LENGTH) {
                return new NextCommand(Messages.Warning.EMPTY_DESCRIPTION.get());
            }
            return new FindCommand(requestArray[1]);

        default:
            return new NextCommand(Messages.Error.INVALID_INSTRUCTION.get());
        }
    }
}