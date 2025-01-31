package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.ui.Ui;

/**
 * Represents an abstract command in the TalkGPT application.
 * <p>
 * This class serves as a blueprint for all commands that modify or interact
 * with the task list. Each specific command (e.g., adding, deleting, marking
 * tasks) extends this class and implements the {@code execute} method.
 * </p>
 *
 * <p>Concrete subclasses include:</p>
 * <ul>
 *     <li>{@code ToDoCommand} - Adds a To-Do task.</li>
 *     <li>{@code DeadlineCommand} - Adds a Deadline task.</li>
 *     <li>{@code EventCommand} - Adds an Event task.</li>
 *     <li>{@code DeleteCommand} - Deletes a task.</li>
 *     <li>{@code ListCommand} - Lists all tasks.</li>
 *     <li>{@code ListOnCommand} - Lists all tasks due a on specific date.</li>
 *     <li>{@code MarkCommand} - Marks a task as completed or unmark a task.</li>
 *     <li>{@code ExitCommand} - Exits the application.</li>
 *     <li>{@code NextCommand} - Go to the next iteration of loop to read user input.</li>
 *     <li>{@code HelpCommand} - Print all available commands to user</li>
 *     <li>{@code ClearCommand} - Clear all tasks in the list</li>
 * </ul>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */
public abstract class Command {

    /**
     * Executes the specific command.
     * <p>
     * Each concrete implementation defines how the command modifies the task list,
     * updates storage, and interacts with the user interface.
     * </p>
     *
     * @param list    The task list to be modified.
     * @param storage The storage system to save task updates.
     * @param ui      The user interface for displaying messages.
     * @return {@code true} if the command signals the application to exit, {@code false} otherwise.
     */
    abstract public boolean execute(TaskList list, Storage storage, Ui ui);
}