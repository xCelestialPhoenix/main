package seedu.nova.model;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.Arrays;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;
import seedu.nova.model.schedule.Week;
import seedu.nova.model.util.Copyable;

/**
 * The type Schedule.
 */
public class Schedule implements Copyable<Schedule> {

    private static final int DAYS_IN_WEEK = 7;
    private static final int ACTUAL_RECESS_WEEK = 7;
    private static final int SCHEDULE_RECESS_WEEK = 16;
    private static final int WEEK_OFFSET = 1;

    private final LocalDate startDate;
    private final LocalDate endDate;

    private Week[] weeks;

    /**
     * Instantiates a new Schedule.
     *
     * @param startDate the start date
     * @param endDate   the end date
     */
    public Schedule(LocalDate startDate, LocalDate endDate) {

        this.startDate = startDate;
        this.endDate = endDate;

        weeks = new Week[17];
    }

    /**
     * Instantiates a new Schedule.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param weeks     the weeks
     */
    public Schedule(LocalDate startDate, LocalDate endDate, Week[] weeks) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.weeks = weeks;
    }

    /**
     * Adds event.
     *
     * @param event the event
     */
    public void addEvent(Event event) {

        LocalDate date = event.getDate();
        int weekNumber = calWeekNumber(date);

        if (weeks[weekNumber] == null) {
            weeks[weekNumber] = new Week(startDate.plusWeeks(weekNumber));
        }

        weeks[weekNumber].addEvent(event);

    }

    /**
     * Adds lesson.
     *
     * @param lesson the lesson
     */
    public void addLesson(Lesson lesson) {

        for (int i = 0; i < 14; i++) {

            if (i == ACTUAL_RECESS_WEEK) {
                //No lesson on recess week
                continue;
            }

            if (weeks[i] == null) {
                weeks[i] = new Week(startDate.plusWeeks(i));
            }
            weeks[i].addLesson(lesson);
        }
    }

    /**
     * Views the schedule on specified date.
     *
     * @param date the date of interest.
     * @return the return message to print on the app.
     */
    public String view(LocalDate date) {

        int weekNumber = calWeekNumber(date);

        if (weeks[weekNumber] == null) {
            return "";
        }

        return weeks[weekNumber].view(date);
    }

    /**
     * Views the schedule of a particular week.
     *
     * @param weekNumber the week of interest.
     * @return the return message to print on the app.
     */
    public String view(int weekNumber) {

        weekNumber = adjustWeekNumber(weekNumber);

        if (weeks[weekNumber] == null) {
            return "";
        }

        return weeks[weekNumber].view();
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {

        return startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {

        return endDate;
    }

    /**
     * Get weeks week [ ].
     *
     * @return the week [ ]
     */
    public Week[] getWeeks() {

        return weeks;
    }

    /**
     * Checks if a date lies within the schedule's period.
     *
     * @param date the date of interest.
     * @return if the date lies within the schedule.
     */
    public boolean checkDateValidity(LocalDate date) {

        return !isBeforeStart(date) && !isAfterEnd(date);
    }

    /**
     * Check if the week number lies within the schedule.
     *
     * @param weekNumber the week number of interest.
     * @return if the week lies within the schedule.
     */
    public boolean checkWeekValidity(int weekNumber) {

        return !isBeforeStart(weekNumber) && !isAfterEnd(weekNumber);
    }

    /**
     * Adjusts the user-entered week number into the corresponding week index in schedule.
     *
     * @param weekNumber the user-entered week number.
     * @return the corresponding week number in schedule
     */
    private int adjustWeekNumber(int weekNumber) {

        if (weekNumber == SCHEDULE_RECESS_WEEK) {

            return ACTUAL_RECESS_WEEK;

        } else if (weekNumber >= ACTUAL_RECESS_WEEK) {

            return weekNumber + 1;

        } else {

            return weekNumber;

        }
    }

    /**
     * An encapsulation to check if a date occurs before the start date of the schedule.
     *
     * @param date the date in question.
     * @return if date is before the schedule's start date.
     */

    private boolean isBeforeStart(LocalDate date) {

        return date.compareTo(startDate) < 0;
    }

    /**
     * An encapsulation to check if the user-entered week number is before the schedule's starting week.
     *
     * @param weekNumber the week number in question.
     * @return if the week number is before the first week of schedule.
     */

    private boolean isBeforeStart(int weekNumber) {

        return weekNumber < 1;
    }

    /**
     * An encapsulation to check if a date occurs after the end date of the schedule.
     *
     * @param date the date in question.
     * @return if date is after the schedule's end date.
     */

    private boolean isAfterEnd(LocalDate date) {

        return date.compareTo(endDate) > 0;
    }

    /**
     * An encapsulation to check if the user-entered week number is after the schedule's ending week.
     *
     * @param weekNumber the week number in question.
     * @return if the week number is after the last week of schedule.
     */

    private boolean isAfterEnd(int weekNumber) {

        return weekNumber > 16;
    }

    /**
     * Calculates the week number of a given date within the schedule.
     *
     * @param date the date of interest.
     * @return the corresponding week number.
     */

    private int calWeekNumber(LocalDate date) {

        return (int) (DAYS.between(startDate, date) / DAYS_IN_WEEK) + WEEK_OFFSET;
    }

    @Override
    public Schedule getCopy() {
        return new Schedule(startDate, endDate, (Week[]) Arrays.stream(weeks).map(Week::getCopy).toArray());
    }

}
