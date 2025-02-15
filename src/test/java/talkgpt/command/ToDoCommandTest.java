package talkgpt.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import talkgpt.TaskList;
import talkgpt.storage.Storage;
import talkgpt.task.Task;
import talkgpt.task.ToDo;
import talkgpt.ui.Ui;

import static org.junit.jupiter.api.Assertions.*;


class ToDoCommandTest {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        storage = new Storage("test_tasks.txt"); // Use a test file to prevent overwriting real data
        ui = new Ui();
    }

    @Test
    void testExecute_AddsToDoTaskSuccessfully() {
        ToDoCommand command = new ToDoCommand("Finish homework");
        String response = command.execute(taskList, storage, ui);

        assertNotNull(response, "execute() should return a valid response");
        assertEquals(1, taskList.size(), "TaskList should contain 1 task");

        Task addedTask = taskList.getTasks().get(0);
        assertInstanceOf(ToDo.class, addedTask, "Added task should be an instance of ToDo");
        assertEquals("Finish homework", addedTask.getDescription(), "Task description should match input");
    }

    @Test
    void testExecute_EmptyDescription() {
        ToDoCommand command = new ToDoCommand("");
        String response = command.execute(taskList, storage, ui);

        assertNotNull(response, "execute() should return a valid response");
        assertEquals(0, taskList.size(), "Task should NOT be added when description is empty");
    }
}