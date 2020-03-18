package seedu.nova.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type Event stub.
 */
public class Event {

    private String description;
    private String venue;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;

    /**
     * Instantiates a new Event stub.
     *
     * @param description the description
     * @param venue       the venue
     * @param startTime   the start time
     * @param endTime     the end time
     * @param date        the date
     */
    public Event(String description, String venue, LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public String toString() {

        return "["
                + description + ", "
                + venue + ", "
                + date + ", "
                + startTime + " - "
                + endTime
                + "]";
    }

}
