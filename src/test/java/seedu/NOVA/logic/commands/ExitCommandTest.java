package seedu.NOVA.logic.commands;

import static seedu.NOVA.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.NOVA.logic.commands.Common.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.NOVA.logic.commands.Common.ExitCommand;
import seedu.NOVA.model.Model;
import seedu.NOVA.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
