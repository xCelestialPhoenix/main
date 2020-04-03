package seedu.nova.logic.commands.commoncommands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.nova.commons.core.LogsCenter;
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
    public static final String MESSAGE_SAME_MODE = "You are already in %1$s mode";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private ModeEnum modeEnum;

    public NavCommand(ModeEnum modeEnum) {
        this.modeEnum = modeEnum;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Set mode in model
        Mode mode = model.getMode();

        if (mode.getModeEnum().equals(this.modeEnum)) {
            return new CommandResult(String.format(MESSAGE_SAME_MODE, modeEnum.name().toLowerCase()), false, false);
        } else if (this.modeEnum.equals(ModeEnum.PROGRESSTRACKER)) {
            mode.setModeEnum(this.modeEnum);

            ProgressTracker pt = model.getProgressTracker();
            Ip ip = pt.getIp();
            Tp tp = pt.getTp();
            double ipProgress = ip.getProgress();
            double tpProgress = tp.getProgress();
            String commandMessage = MESSAGE_SUCCESS + mode.getModeEnum().name() + "\n";

            String messageProgresstracker = "Projects: \n"
                    + "  IP Project: " + ipProgress + "%\n"
                    + "  TP Project: " + tpProgress + "%";
            return new CommandResult(commandMessage + messageProgresstracker, true, false);
        } else {
            mode.setModeEnum(this.modeEnum);
            return new CommandResult(MESSAGE_SUCCESS + mode.getModeEnum().name(), true, false);
        }
    }
}
