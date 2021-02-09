package duke.viewmodel;

import duke.model.Deadline;
import duke.model.Event;
import duke.model.Todo;

public class TaskGenerator {

    /**
     * Generates a new Todo task.
     * @param details Name of Todo task.
     * @param taskIndex Index of Todo task in storage.
     * @return An undone Todo task.
     * @throws DukeException When task name is not given.
     */
    public Todo createTodo(String details, int taskIndex) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException(Constants.EMPTY_TODO);
        }
        return new Todo(taskIndex, details, false);
    }

    /**
     * Generates a Deadline task.
     * @param details Name of Deadline task.
     * @param taskIndex Index of Deadline task in storage.
     * @return An undone Deadline task.
     * @throws DukeException When task name or deadline not given.
     */
    public Deadline createDeadline(String details, int taskIndex) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException(Constants.EMPTY_DEADLINE);
        }
        try {
            String[] deadlineTask = details.split(" /by ");
            String description = deadlineTask[0].trim();
            String deadline = deadlineTask[1].trim();
            return new Deadline(taskIndex, description, false, deadline);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(Constants.EMPTY_DEADLINE_TIME);
        }
    }

    /**
     * Generates an Event task.
     * @param details Name of Event task.
     * @param taskIndex Index of Event task in storage.
     * @return An undone Event task.
     * @throws DukeException When task name or event not given.
     */
    public Event createEvent(String details, int taskIndex) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException(Constants.EMPTY_EVENT);
        }
        try {
            String[] eventTask = details.split(" /at ");
            String description = eventTask[0].trim();
            String event = eventTask[1].trim();
            return new Event(taskIndex, description, false, event);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(Constants.EMPTY_EVENT_TIME);
        }
    }
}
