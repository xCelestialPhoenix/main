package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.EventParser;
import seedu.address.logic.parser.Mode;
import seedu.address.logic.parser.PtParser;
import seedu.address.logic.parser.ScheduleParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;

    private final AddressBookParser addressBookParser;
    private final EventParser eventParser;
    private final ScheduleParser scheduleParser;
    private final PtParser ptParser;
    private Mode mode;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.mode = Mode.ADDRESSBOOK;

        //Create parser objects for each page/ feature
        addressBookParser = new AddressBookParser();
        eventParser = new EventParser();
        scheduleParser = new ScheduleParser();
        ptParser = new PtParser();
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        //Logging, safe to ignore
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;

        if (commandText.contains("nav") && commandText.contains("Progress Tracker")) {
            setMode(Mode.PROGRESSTRACKER);

            commandResult = new CommandResult("Changed mode to Progress Tracker", true, false);

        } else {
            Command command = null;

            //parse and execute based on mode
            switch (mode) {
            case ADDRESSBOOK:
                command = addressBookParser.parseCommand(commandText);
                break;
            case EVENT:
                command = eventParser.parseCommand(commandText);
                break;
            case SCHEDULER:
                command = scheduleParser.parseCommand(commandText);
                break;
            case PROGRESSTRACKER:
                command = ptParser.parseCommand(commandText);
                break;
            default:
                //raise exceptions
            }

            //Parse user input from String to a Command

            //Executes the Command and stores the result
            commandResult = command.execute(model);
        }

        try {
            //We can deduce that the previous line of code modifies model in some way
            // since it's being stored here.
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
