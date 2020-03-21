package seedu.nova.model;

import java.time.LocalDate;
import java.util.List;

import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;

/**
 * The type Week.
 */
public class Week {

    /**
     * The Days.
     */
    final Day[] days;
    /**
     * The Start date.
     */
    final LocalDate startDate;
    /**
     * DateTimeDuration
     */
    final DateTimeSlotList dtsl;

    /**
     * Instantiates a new Week.
     *
     * @param date the date
     */
    public Week(LocalDate date) {

        days = new Day[7];
        startDate = date;
        dtsl = DateTimeSlotList.ofWeek(date);
    }

    /**
     * Add event.
     *
     * @param event the event
     * @throws CommandException the command exception
     */
    public void addEvent(Event event) throws CommandException {

        LocalDate date = event.getDate();
        int day = date.getDayOfWeek().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(date);
        }

        days[day].addEvent(event);
        dtsl.excludeDuration(event.getDtd());
    }

    /**
     * Add lesson.
     *
     * @param lesson the lesson
     * @throws CommandException the command exception
     */
    public void addLesson(Lesson lesson) throws CommandException {

        int day = lesson.getDay().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(startDate.plusDays(day));
        }

        days[day].addLesson(lesson);
        dtsl.excludeDuration(lesson.getDtd());
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

    /**
     * Get free slots in the form of DateTimeDuration
     * @return list of free slots
     */
    public List<DateTimeDuration> getFreeSlots() {
        return dtsl.getSlotList();
    }
}
