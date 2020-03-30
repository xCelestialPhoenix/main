package seedu.nova.model.event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.duration.TimedDuration;
import seedu.nova.model.util.time.duration.WeekDayDuration;

/**
 * Represents an Event that can be managed.
 */
public class Event {
    protected String desc;
    protected String venue;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalDate date;
    protected String note = "NIL";
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

    public LocalTime getStartTime() {
        return startTime;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void markDone() {
        isDone = true;
    }

    public void addNote(String note) {
        this.note = note;
    }

}
