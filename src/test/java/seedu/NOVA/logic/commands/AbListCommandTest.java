package seedu.NOVA.logic.commands;

import static seedu.NOVA.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.NOVA.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.NOVA.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.NOVA.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.NOVA.logic.commands.abcommands.AbListCommand;
import seedu.NOVA.model.Model;
import seedu.NOVA.model.ModelManager;
import seedu.NOVA.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AbListCommand.
 */
public class AbListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new AbListCommand(), model, AbListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new AbListCommand(), model, AbListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
