package seedu.nova.model.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;
import seedu.nova.model.util.Copyable;
import seedu.nova.model.util.time.TimeUtil;

/**
 * The week class of schedule.
 */
public class Week implements Copyable<Week> {

    private final Day[] days;
    private final LocalDate startDate;
    private Set<Event> events;

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

    private Week(Day[] days, LocalDate startDate, Set<Event> events) {
        this.days = days;
        this.startDate = startDate;
        this.events = events;
    }

    /**
     * Adds event.
     *
     * @param event the event
     */
    public void addEvent(Event event) {
        LocalDate date = event.getDate();
        int day = date.getDayOfWeek().getValue() - 1;
        events.add(event);
        days[day].addEvent(event);
    }

    /**
     * Adds lesson.
     *
     * @param lesson the lesson
     */
    public void addLesson(Lesson lesson) {
        int day = lesson.getDay().getValue() - 1;
        events.add(lesson);
        days[day].addLesson(lesson);
    }

    /**
     * gets a particular day
     * @param dow day of week of the day
     * @return the day
     */
    public Day getDay(DayOfWeek dow) {
        return days[dow.getValue() - 1];
    }

    /**
     * View the schedule of a particular day.
     *
     * @param date the date
     * @return the string
     */
    public String view(LocalDate date) {

        int day = date.getDayOfWeek().getValue() - 1;
        return days[day].view();
    }

    /**
     * View string.
     *
     * @return the string
     */
    public String view() {

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Day day : days) {

            index++;

            if (day == null) {

                continue;
            }

            String result = day.view();

            if (!result.equals("")) {

                sb.append(DayOfWeek.of(index));
                sb.append(": \n");
                sb.append(result);

            }
        }
        return sb.toString();
    }

    public boolean hasEvent(Event event) {
        return events.contains(event);
    }

    @Override
    public Week getCopy() {
        return new Week((Day[]) Arrays.stream(days).map(Day::getCopy).toArray(), startDate, new TreeSet<>(events));
    }

}
