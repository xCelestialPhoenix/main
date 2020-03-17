package seedu.NOVA.logic.commands.Common;

import static java.util.Objects.requireNonNull;

import seedu.NOVA.logic.commands.Command;
import seedu.NOVA.logic.commands.CommandResult;
import seedu.NOVA.logic.parser.ModeEnum;
import seedu.NOVA.model.Mode;
import seedu.NOVA.model.Model;

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

        return new CommandResult(MESSAGE_SUCCESS + mode.getModeEnum().name(), true, false);
    }
}
