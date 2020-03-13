package seedu.nova.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.nova.model.Model;
import seedu.nova.model.addressbook.NovaAddressBook;

/**
 * Clears the address book.
 */
public class AbClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new NovaAddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
