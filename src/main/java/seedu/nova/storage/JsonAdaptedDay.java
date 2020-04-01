package seedu.nova.storage;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.nova.model.event.Event;
import seedu.nova.model.schedule.Day;

/**
 * The type Json adapted day.
 */
public class JsonAdaptedDay {

    private final LocalDate date;
    private final List<JsonAdaptedEvent> events = new LinkedList<>();

    /**
     * Instantiates a new Json adapted day.
     *
     * @param date   the date
     * @param events the events
     */
    @JsonCreator
    public JsonAdaptedDay(@JsonProperty("date") LocalDate date,
                          @JsonProperty("events") List<JsonAdaptedEvent> events) {

        this.date = date;
        if (events != null) {
            this.events.addAll(events);
        }
    }

    /**
     * Instantiates a new Json adapted day.
     *
     * @param day the day
     */
    public JsonAdaptedDay(Day day) {
        date = day.getDate();

        //TODO: Populate events
    }

    /**
     * To model type day.
     *
     * @return the day
     */
    public Day toModelType() {

        final LinkedList<Event> eventLinkedList = new LinkedList<>();
        for (JsonAdaptedEvent event : events) {
            eventLinkedList.add(event.toModelType());
        }
        return new Day(eventLinkedList, date, null);
    }
}
