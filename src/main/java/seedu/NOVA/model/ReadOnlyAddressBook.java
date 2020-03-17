package seedu.NOVA.model;

import javafx.collections.ObservableList;
import seedu.NOVA.model.person.Person;

/**
 * Unmodifiable view of an NOVA book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
