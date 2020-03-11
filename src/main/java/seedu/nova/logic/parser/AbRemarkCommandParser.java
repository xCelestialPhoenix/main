package seedu.nova.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.nova.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.nova.commons.core.index.Index;
import seedu.nova.commons.exceptions.IllegalValueException;
import seedu.nova.logic.commands.AbRemarkCommand;
import seedu.nova.logic.parser.exceptions.ParseException;
import seedu.nova.model.person.Remark;

/**
 * Parses input arguments and creates a new {@code AbRemarkCommand} object
 */
public class AbRemarkCommandParser implements Parser<AbRemarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AbRemarkCommand}
     * and returns a {@code AbRemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AbRemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AbRemarkCommand.MESSAGE_USAGE), ive);
        }

        String value = argMultimap.getValue(PREFIX_REMARK).orElse("");
        Remark remark = new Remark(value);

        return new AbRemarkCommand(index, remark);
    }
}
