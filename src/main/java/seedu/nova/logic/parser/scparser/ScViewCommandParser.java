package seedu.nova.logic.parser.scparser;

import static seedu.nova.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nova.commons.core.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_INDEX;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.nova.commons.core.index.Index;
import seedu.nova.logic.commands.sccommands.ScViewCommand;
import seedu.nova.logic.commands.sccommands.ScViewDayCommand;
import seedu.nova.logic.commands.sccommands.ScViewWeekCommand;
import seedu.nova.logic.parser.ArgumentMultimap;
import seedu.nova.logic.parser.ArgumentTokenizer;
import seedu.nova.logic.parser.Parser;
import seedu.nova.logic.parser.ParserUtil;
import seedu.nova.logic.parser.Prefix;
import seedu.nova.logic.parser.exceptions.ParseException;

/**
 * The parser for the view command of schedule.
 */
public class ScViewCommandParser implements Parser<ScViewCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {

        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    @Override
    public ScViewCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_INDEX);

        if ((arePrefixesPresent(argMultimap, PREFIX_DATE) && arePrefixesPresent(argMultimap, PREFIX_INDEX))
                || (!arePrefixesPresent(argMultimap, PREFIX_DATE) && !arePrefixesPresent(argMultimap, PREFIX_INDEX))) {

            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        if (arePrefixesPresent(argMultimap, PREFIX_DATE)) {

            if (!argMultimap.getPreamble().isEmpty()) {

                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
            return new ScViewDayCommand(date);

        } else if (arePrefixesPresent(argMultimap, PREFIX_INDEX)) {

            Index weekNumber = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            return new ScViewWeekCommand(weekNumber.getOneBased());

        } else {
            //We not reach this statement.
            throw new ParseException(MESSAGE_TOO_MANY_ARGUMENTS);
        }

    }

}
