package seedu.NOVA.logic.parser;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.NOVA.logic.commands.EventAddMeetingCommand;
import seedu.NOVA.logic.parser.exceptions.ParseException;
import seedu.NOVA.model.event.Event;
import seedu.NOVA.model.event.Meeting;

/**
 * Parses input arguments and creates a new EventAddMeetingCommand object
 */
public class EventAddMeetingCommandParser implements Parser<EventAddMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EventAddMeetingCommand
     * and returns an EventAddMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EventAddMeetingCommand parse(String args) throws ParseException {


        Event meeting = new Meeting("", "", LocalTime.NOON, LocalTime.NOON, LocalDate.now());
        return new EventAddMeetingCommand(meeting);
    }
}
