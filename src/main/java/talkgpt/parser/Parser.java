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
        if (request.equals("list")) {
            return new ListCommand();
        } else if (request.equals("bye")) {
            return new ExitCommand();
        } else if (request.startsWith("mark") || request.startsWith("unmark")) {
            String[] requestArray = request.split(" ");
            int taskId = Integer.parseInt(requestArray[1]);
            return new MarkCommand(taskId);
        } else if (request.startsWith("delete")) {
            String[] requestArray = request.split(" ");
            if (requestArray.length < 2) {
                ui.showMessage(Messages.Warning.EMPTY_TASK_ID.get());
                return new NextCommand(); //change
            } else {
                int taskId = Integer.parseInt(requestArray[1]);
                return new DeleteCommand(taskId);
            }
        } else if (request.isEmpty()) {
            ui.showMessage(Messages.Warning.EMPTY_COMMAND.get());
            return new NextCommand();
        } else if (request.equals("help")) {
            return new HelpCommand();
        } else if (request.equals("clear")) {
            return new ClearCommand();
        } else if (request.startsWith("list on")) {
            String dateString = request.substring(8).trim(); // Extract the date string
            return new ListOnCommand(dateString);
        } else if (request.startsWith("todo")) {
            if (request.length() <= 5) {
                ui.showMessage(Messages.Warning.EMPTY_DESCRIPTION.get());
                return new NextCommand();
            } else {
                String description = request.substring(5);
                return new ToDoCommand(description);
            }
        } else if (request.startsWith("deadline")) {
            if (!request.contains(" /by ")) {
                ui.showMessage(Messages.Error.INVALID_DEADLINE.get());
                return new NextCommand();
            } else {
                String[] requestBreakDown = request.split(" /by ");
                String description = requestBreakDown[0].substring(9);
                return new DeadlineCommand(description, requestBreakDown[1]);
            }
        } else if (request.startsWith("event")) {
            if (!request.contains(" /from ") || !request.contains(" /to ")) {
                ui.showMessage(Messages.Error.INVALID_EVENT.get());
                return new NextCommand();
            } else {
                String[] duration = request.split(" /from ");
                String description = duration[0].substring(6);
                duration = duration[1].split(" /to ");
                return new EventCommand(description, duration[0], duration[1]);
            }
        } else if (request.startsWith("find")) {
            String[] requestBreakDown = request.split(" ");
            if (requestBreakDown.length < 2) {
                ui.showMessage(Messages.Warning.EMPTY_DESCRIPTION.get());
                return new NextCommand();
            } else {
                return new FindCommand(requestBreakDown[1]);
            }
        } else {
            ui.showMessage(Messages.Error.INVALID_INSTRUCTION.get());
            return new NextCommand();
        }
    }
}