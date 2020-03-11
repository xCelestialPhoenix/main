package seedu.nova.logic.parser;

import static seedu.nova.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.nova.logic.commands.AbFindCommand;
import seedu.nova.logic.parser.exceptions.ParseException;
import seedu.nova.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new AbFindCommand object
 */
public class AbFindCommandParser implements Parser<AbFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AbFindCommand
     * and returns a AbFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AbFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AbFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new AbFindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
