package seedu.nova.model;

import java.time.LocalDate;
import java.util.Arrays;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;
import seedu.nova.model.util.Copyable;

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

    /**
     * Instantiates a new Week.
     *
     * @param date the date
     */
    public Week(LocalDate date) {

        days = new Day[7];
        startDate = date;
    }

    private Week(Day[] days, LocalDate startDate) {
        this.days = days;
        this.startDate = startDate;
    }

    /**
     * Add event.
     *
     * @param event the event
     */
    void addEvent(Event event) {

        LocalDate date = event.getDate();
        int day = date.getDayOfWeek().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(date);
        }

        days[day].addEvent(event);
    }

    /**
     * Add lesson.
     *
     * @param lesson the lesson
     */
    public void addLesson(Lesson lesson) {

        int day = lesson.getDay().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(startDate.plusDays(day));
        }

        days[day].addLesson(lesson);
    }

    /**
     * Deletes an event
     * @param date the date of the event
     * @param index the position of event in list
     */
    public String deleteEvent(LocalDate date, int index) {
        int day = date.getDayOfWeek().getValue() - 1;

        if (days[day] == null) {
            throw new DateNotFoundException();
        }

        return days[day].deleteEvent(index);
    }

    /**
     * Adds a note to an Event.
     * @param desc description of the note
     * @param date the date of the event
     * @param index the position of event in list
     * @return
     */
    public String addNote(String desc, LocalDate date, int index) {
        int day = date.getDayOfWeek().getValue() - 1;

        if (days[day] == null) {
            throw new DateNotFoundException();
        }

        return days[day].addNote(desc, index);
    }

    /**
     * View string.
     *
     * @param date the date
     * @return the string
     */
    public String view(LocalDate date) {

        int day = date.getDayOfWeek().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(startDate.plusDays(day));
        }

        return days[day].view();
    }

    @Override
    public Week getCopy() {
        return new Week((Day[]) Arrays.stream(days).map(Day::getCopy).toArray(), startDate);
    }

}
