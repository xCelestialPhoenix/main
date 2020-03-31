package seedu.nova.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;
import seedu.nova.model.util.Copyable;
import seedu.nova.model.util.time.TimeUtil;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;

/**
 * The type Week.
 */
public class Week implements Copyable<Week> {

    /**
     * The Days.
     */
    final Day[] days;
    /**
     * The Start date.
     */
    final LocalDate startDate;

    final Set<Event> events;

    /**
     * Instantiates a new Week.
     *
     * @param date the date
     */
    public Week(LocalDate date) {
        date = TimeUtil.getMondayOfWeek(date);
        days = new Day[7];
        for (int i = 0; i < 7; i++) {
            days[i] = new Day(date.plusDays(i));
        }
        startDate = date;
        events = new TreeSet<>();
    }

    private Week(Day[] days, LocalDate startDate, TreeSet<Event> events) {
        this.days = days;
        this.startDate = startDate;
        this.events = events;
    }

    /**
     * Add event.
     *
     * @param event the event
     */
    void addEvent(Event event) {
        if (events.contains(event)) {
            return;
        }
        LocalDate date = event.getDate();
        int day = date.getDayOfWeek().getValue() - 1;
        days[day].addEvent(event);
    }

    /**
     * Add lesson.
     *
     * @param lesson the lesson
     */
    public void addLesson(Lesson lesson) {
        if (events.contains(lesson)) {
            return;
        }
        int day = lesson.getDay().getValue() - 1;
        days[day].addLesson(lesson);
    }

    /**
     * View string.
     *
     * @param date the date
     * @return the string
     */
    public String view(LocalDate date) {

        int day = date.getDayOfWeek().getValue() - 1;
        return days[day].view();
    }

    public DateTimeSlotList getFreeSlots(DayOfWeek dow) {
        return days[dow.getValue() - 1].getFreeSlotList();
    }

    @Override
    public Week getCopy() {
        return new Week((Day[]) Arrays.stream(days).map(Day::getCopy).toArray(), startDate, new TreeSet<>(events));
    }

}
