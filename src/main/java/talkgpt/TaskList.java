package talkgpt;

import talkgpt.storage.Storage;
import talkgpt.task.Task;
import talkgpt.ui.Messages;
import talkgpt.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private static final int INDEX_OFFSET = 1;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public boolean isValidID(int id) {
        return id >= 1 && id <= tasks.size();
    }

    public void listTasks(Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage(Messages.Error.EMPTY_TASK_LIST.get());
        } else {
            ui.showMessage(Messages.Info.TASK_LIST.get());
            for (Task task : tasks) {
                ui.showMessage(task.getId() + ". " + task);
            }
        }
    }

    public void addTask(Task task, Storage storage, Ui ui) {
        if (task.isValid()) {
            boolean isDuplicate = tasks.stream()
                    .anyMatch(x -> x.getDescription().equals(task.getDescription()));
            if (!isDuplicate) {
                tasks.add(task);
                storage.saveTasks(tasks);
                ui.showMessage(Messages.Info.TASK_ADDED.get());
                ui.showMessage(task.toString());
            } else {
                ui.showMessage(Messages.Error.DUPLICATE_TASK.get() + task);
            }
        }
    }

    public void handleMark(int taskId, Storage storage, Ui ui) {
        if (!isValidID(taskId)) {
            ui.showMessage(Messages.Error.INVALID_TASK_INDEX.get());
        } else {
            Task updatedTask = tasks.get(taskId - INDEX_OFFSET).toggleStatus();
            tasks.set(taskId - INDEX_OFFSET, updatedTask);
            storage.saveTasks(tasks);
            ui.showMessage(updatedTask.toString());
        }
    }

   public void clear(Storage storage) {
        this.tasks.clear();
        storage.saveTasks(this.tasks);
   }

   public void deleteTask(int taskId, Storage storage, Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage(Messages.Error.EMPTY_TASK_LIST.get());
        } else {
            ui.showMessage(Messages.Info.TASK_DELETED.get());
            ui.showMessage(tasks.get(taskId - INDEX_OFFSET).toString());
            tasks.remove(taskId - INDEX_OFFSET);
            for (int i = taskId - INDEX_OFFSET; i < tasks.size(); i++) {
                tasks.get(i).setId(i + INDEX_OFFSET);
            }
            ui.showFormattedMessage(Messages.Info.TASK_COUNT, tasks.size());
            storage.saveTasks(tasks);
        }
   }

   public void listTaskDueOn(String dueDate, Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage(Messages.Error.EMPTY_TASK_LIST.get());
        } else {
            boolean hasFound = false;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate filterDate = LocalDate.parse(dueDate, formatter);
            ui.showFormattedMessage(Messages.Info.TASK_DUE_ON,
                    filterDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));

            for (Task task : tasks) {
                if(task.isDueOn(filterDate)) {
                    hasFound = true;
                    System.out.println(task);
                }
            }
            if(!hasFound) {
                ui.showMessage(Messages.Info.NO_TASK_ON.get());
            }
        }
   }

   public void findTask(String searchString, Storage storage, Ui ui) {
       boolean hasFound = false;
       int count = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(searchString)) {
                count++;
                hasFound = true;
                ui.showMessage(count + task.toString());
            }
        }

        if (!hasFound) {
            ui.showMessage(Messages.Info.NO_TASK_FOUND.get());
        }
   }
}