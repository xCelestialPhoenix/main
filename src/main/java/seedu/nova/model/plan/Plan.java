package seedu.nova.model.plan;

import java.time.LocalDate;
import java.util.List;

import seedu.nova.model.event.Event;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;

/**
 * task container
 */
public interface Plan {
    void resetPlan();

    List<Task> getTaskList();

    boolean addTask(Task task);

    boolean deleteTask(Task task);

    Task searchTask(String name);

    Event generateTaskEvent(Task task, LocalDate date, DateTimeSlotList dtsl) throws ImpossibleTaskException;
}
