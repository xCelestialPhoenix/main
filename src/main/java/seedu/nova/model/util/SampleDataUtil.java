package seedu.nova.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.nova.model.AddressBook;
import seedu.nova.model.Nova;
import seedu.nova.model.ReadOnlyAddressBook;
import seedu.nova.model.VersionedAddressBook;
import seedu.nova.model.category.Category;
import seedu.nova.model.person.Email;
import seedu.nova.model.person.Name;
import seedu.nova.model.person.Person;
import seedu.nova.model.person.Phone;
import seedu.nova.model.person.Remark;
import seedu.nova.model.progresstracker.Ip;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.PtNote;
import seedu.nova.model.progresstracker.PtTask;
import seedu.nova.model.progresstracker.TaskDesc;
import seedu.nova.model.progresstracker.Tp;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

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

    public static PtTask[] getSamplePtTasks() {
        return new PtTask[] {
            new PtTask(new TaskDesc("task 1"), new Ip(), new PtNote("note 1"), 1, false),
            new PtTask(new TaskDesc("task 2"), new Ip(), new PtNote("note 2"), 1, false),
            new PtTask(new TaskDesc("task 1"), new Tp(), new PtNote("note 1"), 1, true),
            new PtTask(new TaskDesc("task 2"), new Tp(), new PtNote("note 2"), 1, true),
        };
    }

    public static Nova getSampleNova() {
        ReadOnlyAddressBook initialState = new AddressBook();
        VersionedAddressBook sampleAb = new VersionedAddressBook(initialState);
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        ProgressTracker sampleProgressTracker = new ProgressTracker();

        for (PtTask samplePtTask : getSamplePtTasks()) {
            sampleProgressTracker.addPtTask(samplePtTask.getProject().getProjectName(),
                    samplePtTask.getPtWeek(), samplePtTask);
        }

        Nova nova = new Nova();
        nova.setAddressBookNova(sampleAb);
        nova.setProgressTrackerNova(sampleProgressTracker);

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
