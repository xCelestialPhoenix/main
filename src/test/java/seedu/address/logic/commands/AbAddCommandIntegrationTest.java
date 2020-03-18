package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Schedule;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AbAddCommand}.
 */
public class AbAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Schedule(LocalDate.of(2020, 1, 13),
                LocalDate.of(2020, 5, 3)));
    }

    @Test
    public void execute_newPerson_success() {

        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                new Schedule(LocalDate.of(2020, 1, 13), LocalDate.of(2020, 5, 3)));
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AbAddCommand(validPerson), model,
                String.format(AbAddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {

        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AbAddCommand(personInList), model, AbAddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
