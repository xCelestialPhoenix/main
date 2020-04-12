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

/**
 * Adds task to specified week
 */
public class PtAddNoteCommand extends Command {
    public static final String COMMAND_WORD = "addNote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds note to specified task in the "
            + "project in the specified week. "
            + "Parameters: "
            + PREFIX_PROJECT + "PROJECT "
            + PREFIX_WEEK + "WEEK "
            + PREFIX_DESC + "NOTE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT + "Ip "
            + PREFIX_WEEK + "2"
            + PREFIX_TASK + "1"
            + PREFIX_DESC + "take note to do by 2359 Friday";

    public static final String MESSAGE_NOWEEK = "No week beyond week 13";

    public static final String MESSAGE_FAILURE = "Command failed. Please check that there is a task "
            + " or that there isn't an existing note in the specified index";

    public static final String MESSAGE_SUCCESS = "Added note to task %d in week %d of %s";

    private int weekNum;
    private int taskNum;
    private String project;
    private String note;

    public PtAddNoteCommand(int weekNum, int taskNum, String project, String note) {
        requireNonNull(project);
        requireNonNull(note);

        this.weekNum = weekNum;
        this.taskNum = taskNum;
        this.project = project.trim().toLowerCase();
        this.note = note;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public String getProject() {
        return project;
    }

    public String getNote() {
        return note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isOver13 = weekNum > 13;

        if (isOver13) {
            throw new CommandException(MESSAGE_NOWEEK);
        } else {
            boolean isAddSuccess = model.addPtNote(this.project, weekNum, taskNum, note);

            if (!isAddSuccess) {
                throw new CommandException(MESSAGE_FAILURE);
            }

            String projectName = this.project.toUpperCase();
            String result = String.format(MESSAGE_SUCCESS, taskNum, weekNum, projectName);

            return new CommandResult(result, false, false);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PtAddNoteCommand)) {
            return false;
        } else {
            boolean isSameProject = ((PtAddNoteCommand) obj).getProject().equals(this.getProject());
            boolean isSameWeek = ((PtAddNoteCommand) obj).getWeekNum() == this.getWeekNum();
            boolean isSameTaskNum = ((PtAddNoteCommand) obj).getTaskNum() == (this.getTaskNum());
            boolean isSameNote = ((PtAddNoteCommand) obj).getNote().equals(this.getNote());

            return isSameProject && isSameWeek && isSameNote && isSameTaskNum;
        }
    }
}

