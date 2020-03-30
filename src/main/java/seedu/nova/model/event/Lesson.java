package seedu.nova.model.event;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents the lesson type of Event.
 */
public class Lesson extends Event {
    private DayOfWeek day;

    public Lesson(String description, String venue, LocalTime startTime, LocalTime endTime,
                  DayOfWeek day) {
        super(description, venue, startTime, endTime, day);
        this.day = day;
    }

    public Lesson(Lesson lesson) {
        this(lesson.desc, lesson.venue, lesson.startTime, lesson.endTime, lesson.day);
    }

    public DayOfWeek getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Description: " + desc + "\n"
                + "Venue: " + venue + "\n"
                + "Day/Time: " + day.toString() + ", "
                + startTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
                + " - " + endTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "\n"
                + "Note: " + note;
    }
}
