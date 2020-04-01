package seedu.nova.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.nova.model.event.Event;

/**
 * The type Json adapted event.
 */
public class JsonAdaptedEvent {

    /**
     * The Desc.
     */
    protected String desc;

    /**
     * Instantiates a new Json adapted event.
     *
     * @param desc the desc
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("desc") String desc) {
        this.desc = desc;
    }

    /**
     * Instantiates a new Json adapted event.
     *
     * @param event the event
     */
    public JsonAdaptedEvent(Event event) {
        int i = 0;
    }

    /**
     * To model type event.
     *
     * @return the event
     */
    public Event toModelType() {
        return new Event(null, null, null, null, (LocalDate) null);
    }

}
