package seedu.NOVA.logic.commands.AddressBookCommands;

import static java.util.Objects.requireNonNull;
import static seedu.NOVA.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.NOVA.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.NOVA.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.NOVA.logic.commands.Command;
import seedu.NOVA.logic.commands.CommandResult;
import seedu.NOVA.logic.commands.exceptions.CommandException;
import seedu.NOVA.logic.parser.CliSyntax;
import seedu.NOVA.model.Model;
import seedu.NOVA.model.person.Person;


/**
 * Adds a person to the NOVA book.
 */
public class AbAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the NOVA book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + CliSyntax.PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + CliSyntax.PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + CliSyntax.PREFIX_TAG + "friends "
            + CliSyntax.PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the NOVA book";

    private final Person toAdd;

    /**
     * Creates an AbAddCommand to add the specified {@code Person}
     */
    public AbAddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AbAddCommand // instanceof handles nulls
                && toAdd.equals(((AbAddCommand) other).toAdd));
    }
}
