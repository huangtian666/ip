import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class TaskList {

    private ArrayList<Task> tasks;
    private static final int INDEX_OFFSET = 1;

    TaskList() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public boolean isValidID(int id) {
        return id >= 1 && id <= tasks.size();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void listTasks(UI ui) {
        if (tasks.isEmpty()) {
            ui.showMessage(Messages.Error.EMPTY_TASK_LIST.get());
        } else {
            ui.showMessage(Messages.Info.TASK_LIST.get());
            for (Task task : tasks) {
                ui.showMessage(task.getId() + ". " + task);
            }
        }
    }

    void addTask(Task task, Storage storage, UI ui) {
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

    void handleMark(int taskId, Storage storage, UI ui) {
        if (!isValidID(taskId)) {
            ui.showMessage(Messages.Error.INVALID_TASK_INDEX.get());
        } else {
            Task updatedTask = tasks.get(taskId - INDEX_OFFSET).toggleStatus();
            tasks.set(taskId - INDEX_OFFSET, updatedTask);
            storage.saveTasks(tasks);
            ui.showMessage(updatedTask.toString());
        }
    }

    void clear(Storage storage) {
        this.tasks.clear();
        storage.saveTasks(this.tasks);
    }

    void deleteTask(int taskId, Storage storage, UI ui) {
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

    void listTaskDueOn(String dueDate, UI ui) {
        if (tasks.isEmpty()) {
            ui.showMessage(Messages.Error.EMPTY_TASK_LIST.get());
        } else {
            boolean found = false;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate filterDate = LocalDate.parse(dueDate, formatter);
            ui.showFormattedMessage(Messages.Info.TASK_DUE_ON,
                    filterDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));

            for (Task task : tasks) {
                if(task.isDueOn(filterDate)) {
                    found = true;
                    System.out.println(task);
                }
            }
            if(!found) {
                ui.showMessage(Messages.Info.NO_TASK_ON.get());
            }
        }
    }
}