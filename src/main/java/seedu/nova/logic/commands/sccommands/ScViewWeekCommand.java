package seedu.nova.logic.commands.sccommands;

import static java.util.Objects.requireNonNull;

import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;

/**
 * The view week command of schedule.
 */
public class ScViewWeekCommand extends ScViewCommand {

    private static final String MESSAGE_WEEK_OUT_OF_RANGE = "The date is not within the schedule";
    private static final String MESSAGE_WEEK_NO_EVENT = "You have no events on that week";

    private final int weekNumber;

    /**
     * Instantiates a new ScViewWeekCommand object.
     *
     * @param weekNumber the week number to view
     */
    public ScViewWeekCommand(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        if (!model.isWithinSem(weekNumber)) {
            throw new CommandException(MESSAGE_WEEK_OUT_OF_RANGE);
        }

        String message = model.viewSchedule(weekNumber);

        if (message.equals("")) {
            message = MESSAGE_WEEK_NO_EVENT;
        }
        return new CommandResult(message);
    }

}
