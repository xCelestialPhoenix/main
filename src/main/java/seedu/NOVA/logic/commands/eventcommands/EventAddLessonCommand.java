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
 * adds a Lesson into the Schedule.
 */
public class EventAddLessonCommand extends Command {
    public static final String COMMAND_WORD = "lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to the schedule. \n"
            + "Parameters: "
            + PREFIX_DESC + "[description] "
            + PREFIX_VENUE + "[venue] "
            + PREFIX_TIME + "[day] [Start time (HH:MM)] [End time (HH:MM)] "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESC + "CS2103T Tutorial "
            + PREFIX_VENUE + "COM1 B1-03 "
            + PREFIX_TIME + "Friday 10:00 11:00 ";

    public static final String MESSAGE_SUCCESS = "New lesson has been added: \n%1$s";
    private Event toAdd;

    public EventAddLessonCommand(Event lesson) {
        requireNonNull(lesson);
        this.toAdd = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //model.addEvent(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }


}
