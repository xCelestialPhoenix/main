package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.NavCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Class for logic parser
 */
public class LogicParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private final NavCommandParser navCommandParser;
    private final AddressBookParser addressBookParser;
    private final EventParser eventParser;
    private final ScheduleParser scheduleParser;
    private final PtParser ptParser;
    private Model model;

    public LogicParser(Model model) {
        //Create parser objects for each page/ feature
        navCommandParser = new NavCommandParser();
        addressBookParser = new AddressBookParser();
        eventParser = new EventParser();
        scheduleParser = new ScheduleParser();
        ptParser = new PtParser();
        this.model = model;
    }

    /**
     * Parses user input into command for execution.
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();

        if (commandWord.equals(NavCommand.COMMAND_WORD)) {
            return navCommandParser.parse(arguments);
        } else {
            ModeEnum mode = model.getMode().getModeEnum();

            //check mode
            switch (mode) {

            case ADDRESSBOOK:
                return addressBookParser.parseCommand(userInput);

            case EVENT:
                return eventParser.parseCommand(userInput);

            case SCHEDULE:
                return scheduleParser.parseCommand(userInput);

            case PROGRESSTRACKER:
                return ptParser.parseCommand(userInput);

            default:
                throw new ParseException("No such mode");
            }
        }
    }
}
