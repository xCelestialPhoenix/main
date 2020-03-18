package seedu.NOVA.logic.commands;

import static seedu.NOVA.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.NOVA.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.NOVA.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.NOVA.logic.commands.AddressBookCommands.AbRemarkCommand;
import seedu.NOVA.model.AddressBook;
import seedu.NOVA.model.Model;
import seedu.NOVA.model.ModelManager;
import seedu.NOVA.model.UserPrefs;
import seedu.NOVA.model.person.Person;
import seedu.NOVA.model.person.Remark;
import seedu.NOVA.testutil.PersonBuilder;

class AbRemarkCommandTest {
    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRemark(REMARK_STUB).build();

        AbRemarkCommand abRemarkCommand = new AbRemarkCommand(INDEX_FIRST_PERSON,
                new Remark(editedPerson.getRemark().value));

        String expectedMessage = String.format(AbRemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(abRemarkCommand, model, expectedMessage, expectedModel);
    }

}
