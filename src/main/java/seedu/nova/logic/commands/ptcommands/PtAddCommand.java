package seedu.nova.logic.commands.ptcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_PROJECT;
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
import seedu.nova.model.progresstracker.TaskDesc;

/**
 * Adds task to specified week
 */
public class PtAddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds task in the "
            + "project in the specified week. "
            + "Parameters: "
            + PREFIX_PROJECT + "PROJECT "
            + PREFIX_WEEK + "WEEK "
            + PREFIX_DESC + "TASK DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT + "Ip "
            + PREFIX_WEEK + "2"
            + PREFIX_DESC + "Implement javafx";

    private int weekNum;
    private String project;
    private String taskDesc;

    public PtAddCommand(int weekNum, String project, String taskDesc) {
        this.weekNum = weekNum;
        this.project = project.trim().toLowerCase();
        this.taskDesc = taskDesc;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ProgressTracker pt = model.getProgressTracker();
        PtWeek week = null;

        //Create new task
        TaskDesc taskDesc = new TaskDesc(this.taskDesc);
        PtTask newTask = new PtTask(taskDesc, this.weekNum);

        if (project.equals("ip")) {
            Ip ip = pt.getIp();
            PtWeekList weekList = ip.getWeekList();
            week = weekList.getWeek(weekNum);

            //if week not created, create week
            if (week == null) {
                PtWeek newWeek = new PtWeek(weekNum);
                weekList.addWeek(newWeek);

                week = newWeek;
            }

            PtTaskList taskList = week.getTaskList();
            taskList.addTask(newTask);

        } else {
            //do nothing
        }

        String result = "Added task to week " + weekNum + " of " + project;

        return new CommandResult(result, false, false);
    }
}
