package seedu.address.model.event;

import java.time.LocalTime;

/**
 * Represents an Event that can be managed.
 */
public class Event {
    protected String desc;
    protected String venue;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected String note = "";
    protected boolean isDone = false;

    public Event(String desc, String venue, LocalTime startTime, LocalTime endTime) {
        this.desc = desc;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void addNote(String note) {
        this.note = note;
    }

}
