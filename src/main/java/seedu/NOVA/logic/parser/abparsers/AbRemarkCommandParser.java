package seedu.NOVA.logic.parser.abparsers;

import static java.util.Objects.requireNonNull;
import static seedu.NOVA.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.NOVA.commons.core.index.Index;
import seedu.NOVA.commons.exceptions.IllegalValueException;
import seedu.NOVA.logic.commands.abcommands.AbRemarkCommand;
import seedu.NOVA.logic.parser.ArgumentMultimap;
import seedu.NOVA.logic.parser.ArgumentTokenizer;
import seedu.NOVA.logic.parser.CliSyntax;
import seedu.NOVA.logic.parser.Parser;
import seedu.NOVA.logic.parser.ParserUtil;
import seedu.NOVA.logic.parser.exceptions.ParseException;
import seedu.NOVA.model.person.Remark;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AbRemarkCommand.MESSAGE_USAGE), ive);
        }

        String value = argMultimap.getValue(CliSyntax.PREFIX_REMARK).orElse("");
        Remark remark = new Remark(value);

        return new AbRemarkCommand(index, remark);
    }
}
