package talkgpt.parser;

import talkgpt.command.*;
import talkgpt.ui.Messages;
import talkgpt.ui.Ui;

public class Parser {

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
        } else {
            ui.showMessage(Messages.Error.INVALID_INSTRUCTION.get());
            return new NextCommand();
        }
    }
}