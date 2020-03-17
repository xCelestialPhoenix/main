package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.NavCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to parse navigation related commands
 */
public class NavCommandParser implements Parser<NavCommand> {
    @Override
    public NavCommand parse(String args) throws ParseException {
        requireNonNull(args);
        switch (args) {

        case "addressbook":
            return new NavCommand(ModeEnum.ADDRESSBOOK);

        case "event":
            return new NavCommand(ModeEnum.EVENT);

        case "schedule":
            return new NavCommand(ModeEnum.SCHEDULE);

        case "progresstracker":
            return new NavCommand(ModeEnum.PROGRESSTRACKER);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
