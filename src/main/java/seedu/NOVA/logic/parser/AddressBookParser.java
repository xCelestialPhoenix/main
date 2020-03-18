package seedu.NOVA.logic.parser;

import static seedu.NOVA.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.NOVA.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.NOVA.logic.commands.AddressBookCommands.AbAddCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbClearCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbDeleteCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbEditCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbFindCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbHelpCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbListCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbRemarkCommand;
import seedu.NOVA.logic.commands.Command;
import seedu.NOVA.logic.commands.Common.ExitCommand;
import seedu.NOVA.logic.parser.exceptions.ParseException;


/**
 * Parses user input for NOVA book feature.
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

        case AbDeleteCommand.COMMAND_WORD:
            return new AbDeleteCommandParser().parse(arguments);

        case AbClearCommand.COMMAND_WORD:
            return new AbClearCommand();

        case AbFindCommand.COMMAND_WORD:
            return new AbFindCommandParser().parse(arguments);

        case AbListCommand.COMMAND_WORD:
            return new AbListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case AbHelpCommand.COMMAND_WORD:
            return new AbHelpCommand();

        case AbRemarkCommand.COMMAND_WORD:
            return new AbRemarkCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
