package seedu.address.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The interface Event.
 */
public interface Event {

    /**
     * Gets date.
     *
     * @return the date
     */
    LocalDate getDate();

    /**
     * Sets date.
     *
     * @param date the date
     */
    void setDate(LocalDate date);

    /**
     * Gets start time.
     *
     * @return the start time
     */
    LocalTime getStartTime();

    /**
     * Gets end time.
     *
     * @return the end time
     */
    LocalTime getEndTime();

    /**
     * Gets description.
     *
     * @return the description
     */
    String getDescription();

    /**
     * Gets venue.
     *
     * @return the venue
     */
    String getVenue();
}
