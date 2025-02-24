package talkgpt.command;

import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.task.*;
import talkgpt.ui.Messages;
import talkgpt.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    private String description;
    private String start;
    private String end;
    private static final int INDEX_OFFSET = 1;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(TaskList list, Storage storage, Ui ui) {
        if (validateDeadline(this.start) == null || validateDeadline(this.end) == null) {
            return "Invalid start and end date format! Please use 'd/M/yyyy HHmm' (e.g., '25/2/2025 1430').";
        }

        assert validateDeadline(this.start) != null;
        if (validateDeadline(this.start).isAfter(validateDeadline(this.end))) {
            return Messages.Error.WRONG_START_DATE.get();
        }

        assert validateDeadline(this.end) != null;
        if(validateDeadline(this.end).isBefore(LocalDateTime.now())) {
            return Messages.Error.WRONG_DEADLINE.get();
        }

        Task newTask = new Event(list.size() + INDEX_OFFSET, this.description, this.start, this.end);
        return list.addTask(newTask, storage, ui);
    }

    /**
     * Validates and parses the deadline string.
     * @param time The deadline string in "d/M/yyyy HHmm" format.
     * @return A valid LocalDateTime if successful, otherwise null.
     */
    private LocalDateTime validateDeadline(String time) {
        try {
            return LocalDateTime.parse(time, FORMATTER);
        } catch (DateTimeParseException e) {
            return null; // Invalid format
        }
    }

}