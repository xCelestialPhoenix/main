package seedu.nova.model.plan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.nova.model.Schedule;

/**
 * Plan with definite tasks
 */
public class StudyPlan implements Plan {
    private HashMap<TaskDetails, Task> map;

    public StudyPlan() {
        resetPlan();
    }

    @Override
    public void resetPlan() {
        this.map = new HashMap<>();
    }

    @Override
    public List<Task> getTaskList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public boolean addTask(Task task) {
        return map.put(task.details, task) == null;
    }

    @Override
    public boolean deleteTask(Task task) {
        return map.remove(task.details, task);
    }

    @Override
    public Task searchTask(String name) {
        return map.get(TaskDetails.ofName(name));
    }

    @Override
    public boolean generateTaskEvent(Task task, LocalDate date, Schedule sc) throws ImpossibleTaskException {
        return task.generateEventOnDay(date, sc);
    }
}
