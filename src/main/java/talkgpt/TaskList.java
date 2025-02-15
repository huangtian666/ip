package talkgpt;

import talkgpt.storage.Storage;
import talkgpt.task.Task;
import talkgpt.ui.Messages;
import talkgpt.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Manages the list of Task
 * <p>
 * This class handles various task operations such as add, delete, list and so on
 * </p>
 *
 * @author Huang Tian
 * @version 1.0
 * @since 2025-02-01
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private static final int INDEX_OFFSET = 1;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Check if the taskId is valid
     *
     * @param id is the id of the task to be checked
     * @return {@code true} if the ID is valid, {@code false} otherwise.
     */
    public boolean isValidID(int id) {
        return id >= 1 && id <= tasks.size();
    }

    /**
     * Displays the list of tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return Messages.Error.EMPTY_TASK_LIST.get();
        }

        StringBuilder output = new StringBuilder();
        output.append(Messages.Info.TASK_LIST.get()).append("\n");
        for (Task task : tasks) {
            output.append(task.getId() + ". " + task + "\n");
        }
        return output.toString();
    }

    /**
     * Adds a new task to the task list.
     * <p>
     * If the task is valid and not a duplicate, it is added to the list and saved to storage.
     * </p>
     *
     * @param task The task to add.
     * @param storage The storage system to save the task.
     * @param ui The UI used to display messages.
     */
    public String addTask(Task task, Storage storage, Ui ui) {
        assert task != null : "Task cannot be null!";
        assert !task.getDescription().trim().isEmpty() : "Task description cannot be empty!";

        boolean isDuplicate = tasks.stream()
                .anyMatch(x -> x.getDescription().equals(task.getDescription()));
        if (!isDuplicate) {
            tasks.add(task);
            storage.saveTasks(tasks);
            return Messages.Info.TASK_ADDED.get() + "\n" + task;
        } else {
            return Messages.Error.DUPLICATE_TASK.get() + task;
        }
    }
    /**
     * Toggles the completion status of a task.
     *
     * @param taskId The ID of the task to mark as complete or incomplete.
     * @param storage The storage system to update the task status.
     */
    public String handleMark(int taskId, Storage storage) {
        if (!isValidID(taskId)) {
            return Messages.Error.INVALID_TASK_INDEX.get();
        } else {
            Task updatedTask = tasks.get(taskId - INDEX_OFFSET).toggleStatus();
            tasks.set(taskId - INDEX_OFFSET, updatedTask);
            storage.saveTasks(tasks);
            if (updatedTask.getStatus()){
                return Messages.Info.COMPLETE_TASK.get() + "\n" + updatedTask;
            } else {
                return Messages.Info.UNMARK_TASK.get() + "\n" + updatedTask;
            }
        }
    }

    /**
     * Clears all tasks from the task list.
     *
     * @param storage The storage system to update the cleared task list.
     */
   public void clear(Storage storage) {
        this.tasks.clear();
        storage.saveTasks(this.tasks);
   }

    /**
     * Deletes a task from the task list.
     * <p>
     * If the list is empty, an error message is shown. Otherwise, the task is removed,
     * and the remaining tasks are updated.
     * </p>
     *
     * @param taskId The ID of the task to delete.
     * @param storage The storage system to update the task list.
     * @param ui The UI used to display messages.
     */
   public String deleteTask(int taskId, Storage storage, Ui ui) {
        if (tasks.isEmpty()) {
            return Messages.Error.EMPTY_TASK_LIST.get();
        } else {
            ui.showMessage(Messages.Info.TASK_DELETED.get());
            ui.showMessage(tasks.get(taskId - INDEX_OFFSET).toString());
            tasks.remove(taskId - INDEX_OFFSET);
            for (int i = taskId - INDEX_OFFSET; i < tasks.size(); i++) {
                tasks.get(i).setId(i + INDEX_OFFSET);
            }
            storage.saveTasks(tasks);
            return ui.showFormattedMessage(Messages.Info.TASK_COUNT, tasks.size());
        }
   }

    /**
     * Lists all tasks due on a specific date.
     *
     * @param dueDate The due date in the format "d/M/yyyy".
     * @param ui The UI used to display messages.
     */
   public String listTaskDueOn(String dueDate, Ui ui) {
        if (tasks.isEmpty()) {
            return Messages.Error.EMPTY_TASK_LIST.get();
        }

        StringBuilder output = new StringBuilder();
        boolean hasFound = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate filterDate = LocalDate.parse(dueDate, formatter);
        output.append(ui.showFormattedMessage(Messages.Info.TASK_DUE_ON,
                filterDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")))).append("\n");

        for (Task task : tasks) {
            if(task.isDueOn(filterDate)) {
                hasFound = true;
                output.append(task + "\n");
            }
        }

       return hasFound ? output.toString() : Messages.Info.NO_TASK_ON.get();
   }

   public String findTask(String searchString, Ui ui) {
       boolean hasFound = false;
       int count = 0;
       StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchString)) {
                count++;
                hasFound = true;
                output.append(count).append(". ").append(task).append("\n");
            }
        }

       return hasFound ? output.toString() : Messages.Error.NO_TASK_FOUND.get();
   }
}