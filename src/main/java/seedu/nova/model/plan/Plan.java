package seedu.nova.model.plan;

import java.time.LocalDate;
import java.util.List;

import seedu.nova.model.Schedule;

/**
 * task container
 */
public interface Plan {
    void resetPlan();

    List<Task> getTaskList();

    boolean addTask(Task task);

    boolean deleteTask(Task task);

    Task searchTask(String name);

    boolean generateTaskEvent(Task task, LocalDate date, Schedule sc) throws ImpossibleTaskException;
}
