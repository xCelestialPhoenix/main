package seedu.nova.model.schedule;

import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;
import seedu.nova.model.event.EventDetails;
import seedu.nova.model.plan.AbsoluteTask;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.schedule.timeunit.Day;
import seedu.nova.model.schedule.timeunit.Week;

import java.time.LocalDate;
import java.util.List;

public interface SchedulerModel {
    ReadOnlyScheduler getScheduler();

    void setScheduler(ReadOnlyScheduler scheduler);

    DateTimeSlotList getFreeSlot(Week week);

    void replaceWeek(Week week);

    boolean isWithinSem(LocalDate date);

    Day getDay(LocalDate date);

    Week getWeek(LocalDate sameWeekAs);

    void addEvent(Event e);

    void addAbsoluteTask(AbsoluteTask absTask);

    boolean addPlan(Plan plan);

    boolean deletePlan(Plan plan);

    List<Plan> getPlanList();

    Day getDay(LocalDate date, List<Plan> planInAscendingOrder);

    Week getWeek(LocalDate sameWeekAs, List<Plan> planInAscendingOrder);
}
