package seedu.address.model.event;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents the lesson type of Event.
 */
public class Lesson extends Event {
    private DayOfWeek day;

    public Lesson(String desc, String venue, LocalTime startTime, LocalTime endTime, DayOfWeek day) {
        super(desc, venue, startTime, endTime);
        this.day = day;
    }

    @Override
    public String toString() {
        return "Description: " + desc + "\n"
                + "Venue: " + venue + "\n"
                + " " + startTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
                + " - " + endTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
    }
}
