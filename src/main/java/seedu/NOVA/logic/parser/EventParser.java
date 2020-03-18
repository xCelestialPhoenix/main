package seedu.NOVA.logic.parser;

import static seedu.NOVA.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.NOVA.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.NOVA.logic.commands.Command;
import seedu.NOVA.logic.commands.eventcommands.EventAddConsultationCommand;
import seedu.NOVA.logic.commands.eventcommands.EventAddLessonCommand;
import seedu.NOVA.logic.commands.eventcommands.EventAddMeetingCommand;
import seedu.NOVA.logic.commands.eventcommands.EventAddStudyCommand;
import seedu.NOVA.logic.parser.exceptions.ParseException;

/**
 * Parses user input for event feature.
 */
public class EventParser {

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
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case EventAddMeetingCommand.COMMAND_WORD:
            return new EventAddMeetingCommandParser().parse(arguments);

        case EventAddConsultationCommand.COMMAND_WORD:
            return new EventAddConsultationCommandParser().parse(arguments);

        case EventAddStudyCommand.COMMAND_WORD:
            return new EventAddStudyCommandParser().parse(arguments);

        case EventAddLessonCommand.COMMAND_WORD:
            return new EventAddLessonCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

    }
}
