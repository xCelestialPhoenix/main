package seedu.nova.model.schedule;

import java.time.LocalDate;
import java.util.List;

import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;
import seedu.nova.model.plan.AbsoluteTask;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.schedule.timeunit.Day;
import seedu.nova.model.schedule.timeunit.Week;

/**
 * scheduler model
 */
public interface SchedulerModel {
    ReadOnlyScheduler getScheduler();

    void setScheduler(ReadOnlyScheduler scheduler);

    DateTimeSlotList getFreeSlot(Week week);

    void replaceWeek(Week week);

    boolean isWithinSem(LocalDate date);

    void addEvent(Event e);

    void addAbsoluteTask(AbsoluteTask absTask);

    boolean addPlan(Plan plan);

    boolean deletePlan(Plan plan);

    List<Plan> getPlanList();

    Day getDay(LocalDate date);

    /**
     * get day
     * @param date of date
     * @param planInAscendingOrder scheduling plans
     * @return day
     */
    Day getDay(LocalDate date, List<Plan> planInAscendingOrder);

    Week getWeek(LocalDate sameWeekAs);

    Week getWeek(LocalDate sameWeekAs, List<Plan> planInAscendingOrder);
}
