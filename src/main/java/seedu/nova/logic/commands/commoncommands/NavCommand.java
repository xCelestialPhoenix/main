package seedu.nova.logic.commands.commoncommands;

import static java.util.Objects.requireNonNull;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.parser.ModeEnum;
import seedu.nova.model.Mode;
import seedu.nova.model.Model;

/**
 * Class for navigation command
 */
public class NavCommand extends Command {
    public static final String COMMAND_WORD = "nav";
    public static final String MESSAGE_SUCCESS = "Changed mode to ";
    public static final String MESSAGE_SAME_MODE = "You are already in %1$s mode";

    private ModeEnum modeEnum;

    public NavCommand(ModeEnum modeEnum) {
        this.modeEnum = modeEnum;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Set mode in model
        Mode mode = model.getMode();

        if(mode.getModeEnum().equals(this.modeEnum)) {
            return new CommandResult(String.format(MESSAGE_SAME_MODE, modeEnum.name().toLowerCase()), false, false);
        } else {
            mode.setModeEnum(this.modeEnum);
            return new CommandResult(MESSAGE_SUCCESS + mode.getModeEnum().name(), true, false);
        }
    }
}
