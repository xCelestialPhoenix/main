package seedu.nova.model.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;

/**
 * The week class of schedule.
 */
public class Week {

    private final Day[] days;
    private final LocalDate startDate;

    /**
     * Instantiates a new Week.
     *
     * @param date the date
     */
    public Week(LocalDate date) {

        days = new Day[7];
        startDate = date;
    }

    /**
     * Adds event.
     *
     * @param event the event
     */
    public void addEvent(Event event) {

        LocalDate date = event.getDate();
        int day = date.getDayOfWeek().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(date);
        }

        days[day].addEvent(event);
    }

    /**
     * Adds lesson.
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
     * View the schedule of a particular day.
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

}
