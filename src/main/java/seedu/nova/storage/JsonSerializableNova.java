package seedu.nova.storage;

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
import seedu.nova.model.VersionedAddressBook;
import seedu.nova.model.person.Person;
import seedu.nova.model.progresstracker.Ip;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.Project;
import seedu.nova.model.progresstracker.PtTask;
import seedu.nova.model.progresstracker.PtTaskList;
import seedu.nova.model.progresstracker.PtWeek;
import seedu.nova.model.progresstracker.PtWeekList;
import seedu.nova.model.progresstracker.Tp;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "nova")
class JsonSerializableNova {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedPtTask> ptTasks = new ArrayList<>();

    //add your list of adapted class objects here
    /**
     * Constructs a {@code JsonSerializableNova} with the given persons.
     */
    @JsonCreator
    public JsonSerializableNova(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                @JsonProperty("ptTasks") List<JsonAdaptedPtTask> ptTasks) {
        this.persons.addAll(persons);
        this.ptTasks.addAll(ptTasks);
    }

    /**
     * Converts a given {@code Nova} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNova}.
     */
    public JsonSerializableNova(Nova source) {
        persons.addAll(source.getAddressBookNova().getPersonList().stream()
                .map(JsonAdaptedPerson::new).collect(Collectors.toList()));

        ptTasks.addAll(getPtTasks(source));
    }

    /**
     * Converts a given {@code Nova} into a list of adapted objects
     * @param source uture changes to this will not affect the created {@code JsonSerializableNova}.
     * @return a list of JsonAdaptedPtTask
     */
    public List<JsonAdaptedPtTask> getPtTasks(Nova source) {
        List<JsonAdaptedPtTask> list = new ArrayList<>();

        //Add JsonAdaptedPtTasks into ptTasks array
        ProgressTracker pt = source.getProgressTracker();
        Ip ip = pt.getIp();
        Tp tp = pt.getTp();
        PtWeekList ipList = ip.getWeekList();
        PtWeekList tpList = tp.getWeekList();

        for (int i = 1; i < 14; i++) {
            ArrayList<PtTask> currentIpTasks = ipList.getWeek(i).getTaskList().getList();
            ArrayList<PtTask> currentTpTasks = tpList.getWeek(i).getTaskList().getList();

            for (PtTask ptTask : currentIpTasks) {
                JsonAdaptedPtTask task = new JsonAdaptedPtTask(ptTask);
                list.add(task);
            }

            for (PtTask ptTask : currentTpTasks) {
                JsonAdaptedPtTask task = new JsonAdaptedPtTask(ptTask);
                list.add(task);
            }
        }
        return list;
    }

    /**
     * Converts Nova into the model's {@code Nova} object.
     * @return the converted Nova object
     * @throws IllegalValueException
     */
    public Nova toModelType() throws IllegalValueException {
        Nova nova = new Nova();
        VersionedAddressBook ab = toModelTypeAb();
        ProgressTracker pt = toModelTypePt();

        nova.setAddressBookNova(ab);
        nova.setProgressTrackerNova(pt);
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

    /**
     * Converts this progress tracker into the model's {@code ProgressTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ProgressTracker toModelTypePt() throws IllegalValueException {
        ProgressTracker pt = new ProgressTracker();
        Ip ip = pt.getIp();
        Tp tp = pt.getTp();

        for (JsonAdaptedPtTask adaptedPtTask: ptTasks) {
            PtTaskList taskList;
            PtTask modelTask = adaptedPtTask.toModelType();
            Project project = modelTask.getProject();
            int week = modelTask.getPtWeek();

            if (project instanceof Ip) {
                PtWeekList weekList = ip.getWeekList();
                PtWeek ptWeek = weekList.getWeek(week);
                taskList = ptWeek.getTaskList();
            } else {
                PtWeekList weekList = tp.getWeekList();
                PtWeek ptWeek = weekList.getWeek(week);
                taskList = ptWeek.getTaskList();
            }

            //add to correct project and week
            taskList.addTask(modelTask);
        }
        return pt;
    }

}
