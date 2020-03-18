package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AbAddCommand;
import seedu.address.logic.commands.AbClearCommand;
import seedu.address.logic.commands.AbEditCommand;
import seedu.address.logic.commands.AbHelpCommand;
import seedu.address.logic.commands.AbListCommand;
import seedu.address.logic.commands.AbRemarkCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AbHelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AbAddCommand.COMMAND_WORD:
            return new AbAddCommandParser().parse(arguments);

        case AbEditCommand.COMMAND_WORD:
            return new AbEditCommandParser().parse(arguments);

        case seedu.address.logic.commands.AbDeleteCommand.COMMAND_WORD:
            return new AbDeleteCommandParser().parse(arguments);

        case AbClearCommand.COMMAND_WORD:
            return new AbClearCommand();

        case seedu.address.logic.commands.AbFindCommand.COMMAND_WORD:
            return new AbFindCommandParser().parse(arguments);

        case AbListCommand.COMMAND_WORD:
            String[] classmateList = {"classmate"};
            String[] teammateList = {"teammate"};
            if (arguments.trim().equals("")) {
                return new AbListCommand();
            } else {
                return new AbListCategoryCommandParser().parse(arguments);
            }

            /*if (arguments.trim().equals("")) {
                return new AbListCommand();
            } else if (arguments.toLowerCase().trim().equals("classmate")) {
                return new AbListClassmateCommand(new CategoryContainsKeywordsPredicate(Arrays.asList(classmateList)));
            } else if (arguments.toLowerCase().trim().equals("teammate")) {
                return new AbListTeammateCommand(new CategoryContainsKeywordsPredicate(Arrays.asList(teammateList)));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AbHelpCommand.MESSAGE_USAGE));
            } */

        case AbHelpCommand.COMMAND_WORD:
            return new AbHelpCommand();

        case AbRemarkCommand.COMMAND_WORD:
            return new AbRemarkCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
