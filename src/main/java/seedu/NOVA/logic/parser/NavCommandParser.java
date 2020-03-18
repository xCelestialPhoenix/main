package seedu.NOVA.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.NOVA.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.NOVA.logic.commands.Common.NavCommand;
import seedu.NOVA.logic.parser.exceptions.ParseException;

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

        case "scheduler":
            return new NavCommand(ModeEnum.SCHEDULER);

        case "progresstracker":
            return new NavCommand(ModeEnum.PROGRESSTRACKER);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
