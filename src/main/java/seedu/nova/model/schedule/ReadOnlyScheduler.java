package seedu.nova.model.schedule;

import java.util.List;

import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.event.Event;
import seedu.nova.model.plan.AbsoluteTask;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.plan.Task;
import seedu.nova.model.schedule.timeunit.Semester;
import seedu.nova.model.schedule.timeunit.Week;
import seedu.nova.storage.JsonParsable;

/**
 * Read only scheduler
 */
public interface ReadOnlyScheduler extends JsonParsable {
    DateTimeDuration getDateTimeDuration();

    // plans
    List<Plan> getUserDefinedPlanList();

    boolean createAndAddPlan(String name);

    boolean removePlan(Plan plan);

    Plan getDefaultPlan();

    Semester getSem();

    // methods for default plan
    boolean addEvent(Event event);

    boolean deleteEvent(Event event);

    boolean addAbsoluteTask(AbsoluteTask absoluteTask);

    boolean deleteTask(Task task);

    // edit schedule
    void replaceWeek(Week week);
}
