package seedu.nova.testutil;

import static seedu.nova.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.nova.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.nova.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.nova.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.nova.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.nova.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.nova.logic.commands.CommandTestUtil.VALID_TAG_CLASSMATE;
import static seedu.nova.logic.commands.CommandTestUtil.VALID_TAG_TEAMMATE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.nova.commons.exceptions.IllegalValueException;
import seedu.nova.model.AddressBook;
import seedu.nova.model.ReadOnlyAddressBook;
import seedu.nova.model.VersionedAddressBook;
import seedu.nova.model.person.Person;
//import seedu.nova.storage.JsonAdaptedPerson;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253").withRemark("She likes aardvarks.")
            .withTags("classmate").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432").withRemark("He can't take beer!")
            .withTags("classmate").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withTags("classmate").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withTags("classmate").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withTags("classmate").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withTags("classmate").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withTags("teammate").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withTags("teammate").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withTags("classmate").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_CLASSMATE).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_TEAMMATE)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code VersionedAddressBook} with all the typical persons.
     */
    public static VersionedAddressBook getTypicalVersionedAddressBook() {

        ReadOnlyAddressBook initialState;
        AddressBook ab = new AddressBook();

        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        initialState = ab;
        VersionedAddressBook addressBook = new VersionedAddressBook(initialState);

        return addressBook;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
