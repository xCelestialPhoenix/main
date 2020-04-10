package seedu.nova.logic.commands.ptcommands;

import static java.util.Objects.requireNonNull;

import static seedu.nova.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;

/**
 * Adds task to specified week
 */
public class PtDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets specified task as done "
            + "Parameters: "
            + PREFIX_PROJECT + "PROJECT "
            + PREFIX_WEEK + "WEEK "
            + PREFIX_TASK + "TASK \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT + "Ip "
            + PREFIX_WEEK + "2"
            + PREFIX_TASK + "1";

    public static final String MESSAGE_NOWEEK = "No week beyond week 13";
    public static final String MESSAGE_NULLTASK = "No task with that index";

    private int weekNum;
    private String project;
    private int taskNum;

    public PtDoneCommand(int weekNum, String project, int taskNum) {
        this.weekNum = weekNum;
        this.project = project.trim().toLowerCase();
        this.taskNum = taskNum;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isOver13 = weekNum > 13;

        if (isOver13) {
            throw new CommandException(MESSAGE_NOWEEK);
        } else {
            boolean isSetDoneSuccessful = model.setDonePtTask(this.project, weekNum, taskNum);

            if (!isSetDoneSuccessful) {
                throw new CommandException(MESSAGE_NULLTASK);
            }

            String projectName = this.project.toUpperCase();
            String result = "Changed done status of task " + taskNum + " in week " + weekNum + " of "
                    + projectName;

            return new CommandResult(result, false, false);
        }
    }
}

