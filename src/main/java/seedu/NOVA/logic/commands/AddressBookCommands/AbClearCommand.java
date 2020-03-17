package seedu.NOVA.logic.commands.AddressBookCommands;

import static java.util.Objects.requireNonNull;

import seedu.NOVA.logic.commands.Command;
import seedu.NOVA.logic.commands.CommandResult;
import seedu.NOVA.model.AddressBook;
import seedu.NOVA.model.Model;

/**
 * Clears the NOVA book.
 */
public class AbClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
