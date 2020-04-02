package seedu.nova.logic.commands.commoncommands;

import static java.util.Objects.requireNonNull;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.parser.ModeEnum;
import seedu.nova.model.Mode;
import seedu.nova.model.Model;
import seedu.nova.model.progresstracker.Ip;
import seedu.nova.model.progresstracker.ProgressTracker;

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
            double ipProgress = ip.getProgress();

            String messageProgresstracker = "Projects: \n"
                    + "  IP Project: " + ipProgress + "%\n"
                    + "  TP Project: 0%";
            return new CommandResult(messageProgresstracker, false, false);
        }

        return new CommandResult(MESSAGE_SUCCESS + mode.getModeEnum().name(), true, false);
    }
}
