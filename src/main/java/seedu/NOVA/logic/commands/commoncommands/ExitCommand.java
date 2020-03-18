package seedu.NOVA.logic.commands.commoncommands;

import seedu.NOVA.logic.commands.Command;
import seedu.NOVA.logic.commands.CommandResult;
import seedu.NOVA.model.Model;


/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
