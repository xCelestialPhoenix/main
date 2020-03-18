package seedu.NOVA.logic.commands;

import static seedu.NOVA.logic.commands.AddressBookCommands.AbHelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.NOVA.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.NOVA.logic.commands.AddressBookCommands.AbHelpCommand;
import seedu.NOVA.model.Model;
import seedu.NOVA.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new AbHelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
