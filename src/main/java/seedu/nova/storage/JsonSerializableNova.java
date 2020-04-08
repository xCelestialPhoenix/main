package seedu.nova.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.nova.commons.exceptions.IllegalValueException;
import seedu.nova.model.AddressBook;
import seedu.nova.model.Nova;
import seedu.nova.model.ReadOnlyAddressBook;
import seedu.nova.model.Schedule;
import seedu.nova.model.VersionedAddressBook;
import seedu.nova.model.person.Person;
import seedu.nova.model.schedule.event.Event;
import seedu.nova.storage.event.JsonAdaptedEvent;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "nova")
class JsonSerializableNova {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    //add your list of adapted class objects here

    /**
     * Constructs a {@code JsonSerializableNova} with the given persons.
     */
    @JsonCreator
    public JsonSerializableNova(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                @JsonProperty("events") List<JsonAdaptedEvent> events) {
        if (persons != null) {
            this.persons.addAll(persons);
        }
        if (events != null) {
            this.events.addAll(events);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNova}.
     */
    public JsonSerializableNova(Nova source) {
        persons.addAll(source.getAddressBookNova().getPersonList().stream()
                .map(JsonAdaptedPerson::new).collect(Collectors.toList()));

        events.addAll(source.getScheduleNova().getEventList().stream()
                .map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Javadoc
     * @return javadoc
     * @throws IllegalValueException
     */
    public Nova toModelType() throws IllegalValueException {
        Nova nova = new Nova();
        VersionedAddressBook ab = toModelTypeAb();
        Schedule sc = toModelTypeSchedule();

        //Call other toModelType();

        nova.setAddressBookNova(ab);
        nova.setScheduleNova(sc);

        //call other set methods

        return nova;
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public VersionedAddressBook toModelTypeAb() throws IllegalValueException {
        ReadOnlyAddressBook initialState;
        AddressBook ab = new AddressBook();

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (ab.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            ab.addPerson(person);
        }
        initialState = ab;
        VersionedAddressBook addressBook = new VersionedAddressBook(initialState);

        return addressBook;
    }

    //Implement your own classes toModelType methods

    /**
     * Converts this schedule into the model's {@code Schedule} object.
     */
    public Schedule toModelTypeSchedule() throws IllegalValueException {

        Schedule sc = new Schedule(LocalDate.of(2020, 1, 13),
                LocalDate.of(2020, 5, 3));

        for (JsonAdaptedEvent jsonAdaptedEvent: events) {
            Event event = jsonAdaptedEvent.toModelType();

            //if (event instanceof Lesson) {
            //    sc.addLesson((Lesson) event);
            //} else {

            sc.addEvent(event);

            //}

        }

        return sc;
    }

}
