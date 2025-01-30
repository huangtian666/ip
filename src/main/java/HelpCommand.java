class HelpCommand extends Command {

    HelpCommand() {}

    @Override
    boolean execute(TaskList list, Storage storage, UI ui) {
        System.out.println("Available commands:");
        System.out.println("1. list - Display all tasks");
        System.out.println("2. todo <description> - Add a ToDo");
        System.out.println("3. deadline <description> /by <date: dd/mm/yyyy time> - Add a Deadline");
        System.out.println("4. event <description> " +
                "/from <start: dd/mm/yyyy time> /to <end: dd/mm/yyyy time> - Add an Event");
        System.out.println("5. mark <taskId> - Mark a task as completed");
        System.out.println("6. unmark <taskId> - Unmark a task");
        System.out.println("7. delete <taskId> - Delete a task");
        System.out.println("8. bye - Exit the application");
        System.out.println("9. clear - Clear all tasks");
        System.out.println("10. list on <date> - List tasks due on this date");
        System.out.println("11. help - Print all available commands");
        return false;
    }
}