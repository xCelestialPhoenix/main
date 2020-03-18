package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Schedule;
import seedu.address.model.UserPrefs;

public class AbClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new AbClearCommand(), model, AbClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Schedule(LocalDate.of(2020, 1, 13),
                LocalDate.of(2020, 5, 3)));
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                new Schedule(LocalDate.of(2020, 1, 13), LocalDate.of(2020, 5, 3)));
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new AbClearCommand(), model, AbClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
