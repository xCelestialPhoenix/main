package seedu.address.logic.parser;

import seedu.address.logic.commands.EventAddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.Meeting;

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


        Event meeting = new Meeting();
        return new EventAddMeetingCommand(meeting);
    }
}
