package seedu.nova.logic.commands.ptcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;
import seedu.nova.model.progresstracker.Ip;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.PtTask;
import seedu.nova.model.progresstracker.PtTaskList;
import seedu.nova.model.progresstracker.PtWeek;
import seedu.nova.model.progresstracker.PtWeekList;

/**
 * Adds task to specified week
 */
public class PtDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_NULLTASK = "No task with that index";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets specified task as done "
            + "Parameters: "
            + PREFIX_PROJECT + "PROJECT "
            + PREFIX_WEEK + "WEEK "
            + PREFIX_DESC + "TASK DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT + "Ip "
            + PREFIX_WEEK + "2"
            + PREFIX_TASK + "1";

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
        ProgressTracker pt = model.getProgressTracker();
        PtWeek week = null;

        if (project.equals("ip")) {
            Ip ip = pt.getIp();
            PtWeekList weekList = ip.getWeekList();
            week = weekList.getWeek(weekNum);

            //if week not created, create week
            if (week == null) {
                throw new CommandException(MESSAGE_NULLTASK);
            }

            PtTaskList taskList = week.getTaskList();
            PtTask task = taskList.getTask(taskNum);
            task.setDone();

        } else {
            //do nothing
        }

        String result = "Changed done status of task " + taskNum + " in week " + weekNum + " of " + project;

        return new CommandResult(result, false, false);
    }
}

