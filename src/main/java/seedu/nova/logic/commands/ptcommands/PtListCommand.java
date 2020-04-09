package seedu.nova.logic.commands.ptcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;

/**
 * Executes list command
 */
public class PtListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List tasks in the specified "
            + "project in the specified week. "
            + "Parameters: "
            + PREFIX_PROJECT + "PROJECT "
            + PREFIX_WEEK + "WEEK \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT + "Ip "
            + PREFIX_WEEK + "2";

    public static final String MESSAGE_NULLWEEK = "No task in specified week";

    public static final String MESSAGE_NOWEEK = "No week beyond week 13";

    private int weekNum;
    private String project;

    public PtListCommand(int weekNum, String project) {
        this.weekNum = weekNum;
        this.project = project.trim().toLowerCase();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isOver13 = weekNum > 13;

        if (isOver13) {
            throw new CommandException(MESSAGE_NOWEEK);
        } else {
            String listResult = model.listPtTask(this.project, weekNum);

            boolean noTask = listResult.equals("");

            if (noTask) {
                throw new CommandException(MESSAGE_NULLWEEK);
            }

            String header = this.project.toUpperCase() + " Project " + "(Week " + weekNum + "):" + "\n";
            String result = header + listResult;

            return new CommandResult(result, false, false);
        }
    }
}
