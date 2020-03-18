package seedu.NOVA.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.NOVA.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.NOVA.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.NOVA.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.NOVA.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.NOVA.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.NOVA.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.NOVA.commons.core.Messages;
import seedu.NOVA.commons.core.index.Index;
import seedu.NOVA.logic.commands.abcommands.AbDeleteCommand;
import seedu.NOVA.model.Model;
import seedu.NOVA.model.ModelManager;
import seedu.NOVA.model.UserPrefs;
import seedu.NOVA.model.person.Person;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code AbDeleteCommand}.
 */
public class AbDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AbDeleteCommand abDeleteCommand = new AbDeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(
                AbDeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(abDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AbDeleteCommand abDeleteCommand = new AbDeleteCommand(outOfBoundIndex);

        assertCommandFailure(abDeleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AbDeleteCommand abDeleteCommand = new AbDeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(
                AbDeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(abDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of NOVA book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AbDeleteCommand abDeleteCommand = new AbDeleteCommand(outOfBoundIndex);

        assertCommandFailure(abDeleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AbDeleteCommand deleteFirstCommand = new AbDeleteCommand(INDEX_FIRST_PERSON);
        AbDeleteCommand deleteSecondCommand = new AbDeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        AbDeleteCommand deleteFirstCommandCopy = new AbDeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
