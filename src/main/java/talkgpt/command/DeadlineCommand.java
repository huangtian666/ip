package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.task.*;
import talkgpt.ui.Messages;
import talkgpt.ui.Ui;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private String description;
    private String deadline;
    private static final int INDEX_OFFSET = 1;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        LocalDateTime parsedDeadline = validateDeadline(deadline);
        if (parsedDeadline == null) {
            return "Invalid deadline format! Please use 'd/M/yyyy HHmm' (e.g., '25/2/2025 1430').";
        }

        if (parsedDeadline.isBefore(LocalDateTime.now())) {
            return Messages.Error.WRONG_DEADLINE.get();
        }
        Task newTask = new Deadline(list.size() + INDEX_OFFSET, description, deadline);
        return list.addTask(newTask, storage, ui);
    }

    /**
     * Validates and parses the deadline string.
     * @param deadline The deadline string in "d/M/yyyy HHmm" format.
     * @return A valid LocalDateTime if successful, otherwise null.
     */
    private LocalDateTime validateDeadline(String deadline) {
        try {
            return LocalDateTime.parse(deadline, FORMATTER);
        } catch (DateTimeParseException e) {
            return null; // Invalid format
        }
    }
}