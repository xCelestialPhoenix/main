package seedu.address.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type Event stub.
 */
public class EventStub implements Event {

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
    public EventStub(String description, String venue, LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getVenue() {
        return venue;
    }

    @Override
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
