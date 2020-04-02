package seedu.nova.logic.commands.commoncommands;

import static java.util.Objects.requireNonNull;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.parser.ModeEnum;
import seedu.nova.model.Mode;
import seedu.nova.model.Model;
import seedu.nova.model.progresstracker.Ip;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.Tp;

/**
 * Class for navigation command
 */
public class NavCommand extends Command {
    public static final String COMMAND_WORD = "nav";
    public static final String MESSAGE_SUCCESS = "Changed mode to ";

    private ModeEnum modeEnum;

    public NavCommand(ModeEnum modeEnum) {
        this.modeEnum = modeEnum;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Set mode in model
        Mode mode = model.getMode();
        mode.setModeEnum(this.modeEnum);

        if (this.modeEnum.equals(ModeEnum.PROGRESSTRACKER)) {
            ProgressTracker pt = model.getProgressTracker();
            Ip ip = pt.getIp();
            Tp tp = pt.getTp();

            double ipProgress = ip.getProgress();
            double tpProgress = tp.getProgress();

            String messageProgressTracker = "Projects: \n"
                    + "  IP Project: " + ipProgress + "%\n"
                    + "  TP Project: " + tpProgress + "%\n";
            return new CommandResult(messageProgressTracker, false, false);
        }

        return new CommandResult(MESSAGE_SUCCESS + mode.getModeEnum().name(), true, false);
    }
}
