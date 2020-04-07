package seedu.nova.storage.event;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.nova.commons.exceptions.IllegalValueException;
import seedu.nova.logic.parser.ParserUtil;
import seedu.nova.model.schedule.event.Consultation;
import seedu.nova.model.schedule.event.Event;
import seedu.nova.model.schedule.event.Meeting;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String eventType;
    private final String desc;
    private final String venue;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final String note;


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventType") String eventType,
                            @JsonProperty("desc") String desc,
                            @JsonProperty("venue") String venue,
                            @JsonProperty("date") String date,
                             @JsonProperty("startTime") String startTime,
                             @JsonProperty("endTime") String endTime,
                             @JsonProperty("note") String note) {
        this.eventType = eventType;
        this.desc = desc;
        this.venue = venue;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.note = note;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        eventType = source.getEventType();
        desc = source.getDesc();
        venue = source.getVenue();
        date = source.getDate().toString();
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
        note = source.getNote();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        if (eventType.equals("meeting")) {
            return toMeeting();

        } else if (eventType.equals("consultation")) {
            return toConsultation();

        } else if (eventType.equals("study")) {
            return toStudy();

        } else if (eventType.equals("lesson")) {
            return toLesson();

        } else {
            throw new IllegalValueException("Unrecognised event type.");
        }

    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Meeting} object.
     * @return an Event
     * @throws IllegalValueException
     */
    public Event toMeeting() throws IllegalValueException {
        checkFields();

        LocalDate localDate = ParserUtil.parseDate(date);
        LocalTime start = ParserUtil.parseTime(startTime);
        LocalTime end = ParserUtil.parseTime(endTime);

        Event meeting = new Meeting(desc, venue, start, end, localDate, note);

        return meeting;
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Consultation} object.
     * @return an Event
     * @throws IllegalValueException
     */
    public Event toConsultation() throws IllegalValueException {
        checkFields();

        LocalDate localDate = ParserUtil.parseDate(date);
        LocalTime start = ParserUtil.parseTime(startTime);
        LocalTime end = ParserUtil.parseTime(endTime);

        Event consultation = new Consultation(desc, venue, start, end, localDate, note);

        return consultation;
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code StudySession} object.
     * @return an Event
     * @throws IllegalValueException
     */
    public Event toStudy() throws IllegalValueException {
        checkFields();

    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Lesson} object.
     * @return an Event
     * @throws IllegalValueException
     */
    public Event toLesson() throws IllegalValueException {
        checkFields();

    }

    /**
     * Checks for any missing fields.
     * @throws IllegalValueException
     */
    public void checkFields() throws IllegalValueException {
        if (desc == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "venue"));
        }

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "start time"));
        }

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "end time"));
        }

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date"));
        }

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "note"));
        }
    }

}
