package seedu.nova.model.event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.duration.TimedDuration;
import seedu.nova.model.util.time.duration.WeekDayDuration;

/**
 * Represents an Event that can be managed.
 */
public class Event implements Comparable<Event> {
    protected String desc;
    protected String venue;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalDate date;
    protected String note = "";
    protected boolean isDone = false;
    protected TimedDuration dtd;

    public Event(String desc, String venue, LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.desc = desc;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.dtd = new DateTimeDuration(date, startTime, endTime);
    }

    public Event(String desc, String venue, LocalTime startTime, LocalTime endTime, DayOfWeek dow) {
        this.desc = desc;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = null;
        this.dtd = new WeekDayDuration(dow, startTime, endTime);
    }

    public boolean getIsDone() {
        return isDone;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(getDate(), getStartTime());
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public TimedDuration getDtd() {
        return dtd;
    }

    public DayOfWeek getDayOfWeek() {
        return dtd.getStartDay();
    }

    public void markDone() {
        isDone = true;
    }

    public void addNote(String note) {
        this.note = note;
    }

    @Override
    public int compareTo(Event o) {
        return getStartDateTime().compareTo(o.getStartDateTime());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            return desc.equals(((Event) obj).desc)
                    && date.equals(((Event) obj).date)
                    && startTime.equals(((Event) obj).startTime)
                    && endTime.equals(((Event) obj).endTime);
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc, date, startTime, endTime);
    }
}
