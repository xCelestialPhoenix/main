package seedu.NOVA.logic.commands.eventcommands;

import static java.util.Objects.requireNonNull;
import static seedu.NOVA.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.NOVA.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.NOVA.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.NOVA.logic.commands.Command;
import seedu.NOVA.logic.commands.CommandResult;
import seedu.NOVA.logic.commands.exceptions.CommandException;
import seedu.NOVA.model.Model;
import seedu.NOVA.model.event.Event;

/**
 * adds a StudySession into the Schedule.
 */
public class EventAddStudyCommand extends Command {
    public static final String COMMAND_WORD = "study";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a study session to the schedule. \n"
            + "Parameters: "
            + PREFIX_DESC + "[description] "
            + PREFIX_VENUE + "[venue] "
            + PREFIX_TIME + "[YYYY-MM-DD] [Start time (HH:MM)] [End time (HH:MM)] "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESC + "cool peeps revision "
            + PREFIX_VENUE + "COM1 Basement "
            + PREFIX_TIME + "2020-03-19 14:00 15:00 ";

    public static final String MESSAGE_SUCCESS = "New study session has been added: \n%1$s";
    private Event toAdd;

    public EventAddStudyCommand(Event study) {
        requireNonNull(study);
        this.toAdd = study;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //model.addEvent(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
