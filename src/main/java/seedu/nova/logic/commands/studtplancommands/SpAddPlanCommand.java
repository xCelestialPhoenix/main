package seedu.nova.logic.commands.studtplancommands;

import static java.util.Objects.requireNonNull;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;
import seedu.nova.model.plan.AbsolutePlan;

/**
 * Command for adding plan
 */
public class SpAddPlanCommand extends Command {
    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "add";
    private static final String SUCCESS = "Success!";
    private static final String FAILED = "Haih...";

    private final String name;

    /**
     * Instantiates a new Sc view day command.
     *
     * @param name name
     */
    public SpAddPlanCommand(String name) {
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        if (!model.getSchedulerManager().addPlan(new AbsolutePlan(this.name))) {
            throw new CommandException(FAILED);
        } else {
            return new CommandResult(SUCCESS);
        }
    }
}
