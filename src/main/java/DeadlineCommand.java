class DeadlineCommand extends Command {

    private String description;
    private String deadline;
    private static final int INDEX_OFFSET = 1;

    DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    boolean execute(TaskList list, Storage storage, UI ui) {
        Task newTask = new Deadline(list.size() + INDEX_OFFSET, description, deadline);
        list.addTask(newTask, storage, ui);
        return false;
    }
}