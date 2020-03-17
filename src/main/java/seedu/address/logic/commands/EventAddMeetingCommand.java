package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * adds an Event into the Schedule.
 */
public class EventAddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meeting";
    public static final String MESSAGE_SUCCESS = "New meeting has been added: \n%1$s";
    private Event toAdd;

    public EventAddMeetingCommand(Event meeting) {
        requireNonNull(meeting);
        this.toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //model.addEvent(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
