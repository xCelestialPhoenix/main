package seedu.NOVA.logic.commands;

import static seedu.NOVA.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.NOVA.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.NOVA.logic.commands.abcommands.AbClearCommand;
import seedu.NOVA.model.AddressBook;
import seedu.NOVA.model.Model;
import seedu.NOVA.model.ModelManager;
import seedu.NOVA.model.UserPrefs;

public class AbClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new AbClearCommand(), model, AbClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new AbClearCommand(), model, AbClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
