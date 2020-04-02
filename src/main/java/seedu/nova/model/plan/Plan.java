package seedu.nova.model.plan;

import java.util.List;

import seedu.nova.model.schedule.Day;
import seedu.nova.model.schedule.event.Event;

/**
 * task container
 */
public interface Plan {
    void resetPlan();

    List<Task> getTaskList();

    boolean addTask(Task task);

    boolean deleteTask(Task task);

    Task searchTask(String name);

    Event generateTaskEvent(Task task, Day day) throws ImpossibleTaskException;
}
