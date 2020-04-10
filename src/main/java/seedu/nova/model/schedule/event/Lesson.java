package seedu.nova.model.schedule.event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents the lesson type of Event.
 */
public class Lesson extends Event {
    private static final String EVENT_TYPE = "lesson";
    private DayOfWeek day;


    public Lesson(String description, String venue, LocalTime startTime, LocalTime endTime,
                   DayOfWeek day) {
        super(description, venue, startTime, endTime, day);
        this.day = day;
    }

    public Lesson(String description, String venue, LocalTime startTime, LocalTime endTime,
                  DayOfWeek day, LocalDate date, String note) {
        super(description, venue, startTime, endTime, day);
        this.day = day;
        this.date = date;
        this.note = note;
    }

    public Lesson(Lesson lesson) {
        this(lesson.desc, lesson.venue, lesson.startTime, lesson.endTime, lesson.day);
    }

    @Override
    public String getEventType() {
        return EVENT_TYPE;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            return desc.equals(((Event) obj).desc)
                    && startTime.equals(((Event) obj).startTime)
                    && endTime.equals(((Event) obj).endTime)
                    && day.equals(((Lesson) obj).day);
        } else {
            return super.equals(obj);
        }
    }
}
