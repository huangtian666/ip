class EventCommand extends Command {

    private String description;
    private String start;
    private String end;
    private static final int INDEX_OFFSET = 1;

    EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    boolean execute(TaskList list, Storage storage, UI ui) {
        Task newTask = new Event(list.size() + INDEX_OFFSET, this.description, this.start, this.end);
        list.addTask(newTask, storage, ui);
        return false;
    }

}