package seedu.nova.model.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.nova.model.AddressBook;
import seedu.nova.model.Nova;
import seedu.nova.model.ReadOnlyAddressBook;
import seedu.nova.model.Schedule;
import seedu.nova.model.VersionedAddressBook;
import seedu.nova.model.category.Category;
import seedu.nova.model.person.Email;
import seedu.nova.model.person.Name;
import seedu.nova.model.person.Person;
import seedu.nova.model.person.Phone;
import seedu.nova.model.person.Remark;
import seedu.nova.model.schedule.event.Consultation;
import seedu.nova.model.schedule.event.Event;
import seedu.nova.model.schedule.event.Lesson;
import seedu.nova.model.schedule.event.Meeting;
import seedu.nova.model.schedule.event.StudySession;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");
    public static final LocalDate SAMPLE_START_DATE = LocalDate.of(2020, 1, 13);
    public static final LocalDate SAMPLE_END_DATE = LocalDate.of(2020, 5, 3);

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("teammate"), EMPTY_REMARK),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getTagSet("teammate"), EMPTY_REMARK),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getTagSet("teammate"), EMPTY_REMARK),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getTagSet("teammate"), EMPTY_REMARK),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getTagSet("teammate"), EMPTY_REMARK),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getTagSet("teammate"), EMPTY_REMARK)
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
            new Meeting("CS2103T DG Meeting", "COM1 Basement", LocalTime.parse("13:00"),
                    LocalTime.parse("14:00"), LocalDate.parse("2020-04-06"), "to do Logic portion"),
            new Consultation("CS2103T UML Diagram", "COM1 B01-03", LocalTime.parse("11:00"),
                    LocalTime.parse("12:00"), LocalDate.parse("2020-04-06"), "ask about sequence diagrams"),
            new StudySession("cool peeps study", "on zoom", LocalTime.parse("14:00"),
                    LocalTime.parse("16:00"), LocalDate.parse("2020-04-05"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-01-17"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-01-24"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-01-31"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-02-07"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-02-14"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-02-21"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-03-06"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-03-13"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-03-20"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-03-27"), "NIL"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-04-03"), "finding bugs"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-04-10"), "product demo rehearsal!"),
            new Lesson("CS2103T Tutorial", "COM1 B1-03", LocalTime.parse("10:00"),
                    LocalTime.parse("11:00"), DayOfWeek.FRIDAY,
                    LocalDate.parse("2020-04-17"), "product demo!")

        };
    }

    public static Nova getSampleNova() {
        ReadOnlyAddressBook initialState = new AddressBook();
        VersionedAddressBook sampleAb = new VersionedAddressBook(initialState);
        Schedule sampleSchedule = new Schedule(SAMPLE_START_DATE, SAMPLE_END_DATE);

        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Event sampleEvent : getSampleEvents()) {
            sampleSchedule.addEvent(sampleEvent);
        }

        Nova nova = new Nova();
        nova.setAddressBookNova(sampleAb);
        nova.setScheduleNova(sampleSchedule);

        return nova;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Category> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Category::new)
                .collect(Collectors.toSet());
    }

}
