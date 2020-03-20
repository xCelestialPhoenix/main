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
 * Manager for scheduler, following the convention
 */
public class SchedulerManager implements SchedulerModel {
    private final Scheduler scheduler;

    public SchedulerManager(ReadOnlyScheduler readOnlyScheduler) {
        this.scheduler = new Scheduler(readOnlyScheduler);
    }

    @Override
    public ReadOnlyScheduler getScheduler() {
        return this.scheduler;
    }

    @Override
    public void setScheduler(ReadOnlyScheduler scheduler) {
        this.scheduler.resetData(scheduler);
    }

    @Override
    public DateTimeSlotList getFreeSlot(Week week) {
        return week.getFreeSlotList();
    }

    @Override
    public void addEvent(Event e) {
        this.scheduler.addEvent(e);
    }

    @Override
    public void addAbsoluteTask(AbsoluteTask absTask) {
        this.scheduler.addAbsoluteTask(absTask);
    }

    @Override
    public boolean addPlan(Plan plan) {
        return this.scheduler.getUserDefinedPlanList().add(plan);
    }

    @Override
    public boolean deletePlan(Plan plan) {
        return this.scheduler.getUserDefinedPlanList().remove(plan);
    }

    @Override
    public Day getDay(LocalDate date) {
        return this.scheduler.getSem().getDay(date).getCopy();
    }

    @Override
    public Day getDay(LocalDate date, List<Plan> planInAscendingOrder) {
        Week wk = getWeek(date, planInAscendingOrder);
        return wk.getDay(date.getDayOfWeek());
    }

    @Override
    public Week getWeek(LocalDate sameWeekAs) {
        return getWeek(sameWeekAs, null);
    }

    @Override
    public Week getWeek(LocalDate sameWeekAs, List<Plan> planList) {
        Week wk = this.scheduler.getSem().getWeek(sameWeekAs).getCopy();
        this.scheduler.getDefaultPlan().scheduleEvents(wk);
        if (wk != null && planList != null) {
            for (Plan plan : planList) {
                plan.scheduleEvents(wk);
            }
        }
        return wk;
    }

    @Override
    public List<Plan> getPlanList() {
        return this.scheduler.getUserDefinedPlanList();
    }

    @Override
    public void replaceWeek(Week week) {
        this.scheduler.replaceWeek(week);
    }

    @Override
    public boolean isWithinSem(LocalDate date) {
        return this.scheduler.getSem().getDuration().getStartDate().compareTo(date) <= 0
                && this.scheduler.getSem().getDuration().getEndDate().compareTo(date) >= 0;
    }
}
