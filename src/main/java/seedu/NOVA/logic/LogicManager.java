package seedu.NOVA.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.NOVA.commons.core.GuiSettings;
import seedu.NOVA.commons.core.LogsCenter;
import seedu.NOVA.logic.commands.Command;
import seedu.NOVA.logic.commands.CommandResult;
import seedu.NOVA.logic.commands.exceptions.CommandException;
import seedu.NOVA.logic.parser.LogicParser;
import seedu.NOVA.logic.parser.exceptions.ParseException;
import seedu.NOVA.model.Model;
import seedu.NOVA.model.ReadOnlyAddressBook;
import seedu.NOVA.model.person.Person;
import seedu.NOVA.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;

    private final LogicParser logicParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        logicParser = new LogicParser(model);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        //Logging, safe to ignore
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = logicParser.parseCommand(commandText);

        commandResult = command.execute(model);

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

    @Override
    public Model getModel() {
        return this.model;
    }
}
